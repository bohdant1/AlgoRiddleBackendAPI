package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.Converters.QuestionConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.TestCase.TestCaseResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.QuestionRepository;
import com.algoriddle.AlgoRiddleBackendApi.Request.Model.SubmissionRequestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SubmissionServiceProvider implements SubmissionService{
    private final QuestionRepository questionRepo;

    @Override
    public String submitQuestion(SubmissionRequestDTO submissionRequestDTO) throws JsonProcessingException {
        //1. Generate a unique submission id
        UUID submissionID = UUID.randomUUID();

        //2. Generate an executable
        String executable = generateExecutable(submissionRequestDTO, submissionID);

        //2.1 Format the executable

        // Replace four spaces with a tab character
        executable = executable.replaceAll(" {4}", "\t");
//
//        // Convert the indented value to a JSON-formatted string
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String formattedExecutable = gson.toJson(indentedValue);

        //3. Send the executable to judge0

        RestTemplate restTemplate = new RestTemplate();
        String url
                = "http://localhost:2358/submissions/?base64_encoded=false&wait=true";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SubmissionRequestModel submissionRequestModel = new SubmissionRequestModel(executable);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(submissionRequestModel);

        // Wrap the object in HttpEntity
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        //4. Interpret the result

        //5. Persist the result

        //6. Return the TestCaseResponseDTO
        return response.getBody();
    }

    private String generateExecutable(SubmissionRequestDTO submissionRequestDTO, UUID submissionID){
        Question question = this.questionRepo
                .findQuestionByID(submissionRequestDTO.getQuestionID())
                .orElse(null);

        // Append the (solution) code with all the test cases
        StringBuilder executable = new StringBuilder(submissionRequestDTO.sourceCode + "\n");

        for(TestCase testCase : question.testCases){
            executable.append(testCase.code)
                    .append("\n");
        }

        String submissionIdFormated = submissionID.toString().replace("-", "");
        String evaluationDictName = "evaluationDict"+submissionIdFormated;

        // Add the evaluation in the bottom. Run every test case.
        StringBuilder evaluation = new StringBuilder(evaluationDictName+"={}\n");

        for(TestCase testCase : question.testCases){
            evaluation.append(evaluationDictName+"[\""+testCase.getID()+"\"]")
                    .append("=")
                    .append(testCase.getMethodName()+"()")
                    .append("\n");
        }

        evaluation.append("print(\"").append(evaluationDictName).append("\",").append(evaluationDictName).append(")");


        executable.append(evaluation);

        // Return the full executable
        return executable.toString();
    }


}

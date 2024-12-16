package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionEvaluation;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionEvaluationRecord;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionResponseModel;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.QuestionRepository;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionRequestModel;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.TestCaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class SubmissionServiceProvider implements SubmissionService{
    private final QuestionRepository questionRepo;
    private final TestCaseRepository testCaseRepository;

    @Override
    public SubmissionEvaluation submitQuestion(SubmissionRequestDTO submissionRequestDTO) throws JsonProcessingException {
        //1. Generate a unique submission id
        UUID submissionID = UUID.randomUUID();

        //2. Generate an executable
        String executable = generateExecutable(submissionRequestDTO, submissionID);

        //2.1 Format the executable

        // Replace four spaces with a tab character
        executable = executable.replaceAll(" {4}", "\t");

        //3. Send the executable to judge0

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:2358/submissions/?base64_encoded=false&wait=true";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SubmissionRequestModel submissionRequestModel = new SubmissionRequestModel(executable);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(submissionRequestModel);

        // Wrap the object in HttpEntity
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        // Send POST request
        ResponseEntity<SubmissionResponseModel> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                SubmissionResponseModel.class
        );

        //4. Interpret the result
        SubmissionEvaluation evaluation = evaluate(response.getBody(), submissionID);

        //5. Persist the result

        //6. Return the TestCaseResponseDTO
        return evaluation;
    }

    private String generateExecutable(SubmissionRequestDTO submissionRequestDTO, UUID submissionID){
        //TODO Consider null safety
        Question question = this.questionRepo
                .findQuestionByID(submissionRequestDTO.getQuestionID())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

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

    public SubmissionEvaluation evaluate(SubmissionResponseModel responseModel, UUID submissionId){
        if(responseModel.getStdout()!=null){
            return doEvaluate(responseModel.getStdout(), submissionId);
        } else if (responseModel.getStderr()!=null) {
            return new SubmissionEvaluation(submissionId.toString(), null, responseModel.getStderr());
        }
        return null;
    }

    public SubmissionEvaluation doEvaluate(String stdout, UUID submissionId) {
        // Extract all test case data
        List<SubmissionEvaluationRecord> records = extractRecords(stdout);

        return new SubmissionEvaluation(submissionId.toString(), records, null);
    }


    private List<SubmissionEvaluationRecord> extractRecords(String stdout) {
        List<SubmissionEvaluationRecord> records = new ArrayList<>();

        // Regex to capture the patterns of each test case
        Pattern pattern = Pattern.compile(
                "'([a-f0-9\\-]+)': \\((\\w+), '([^']*)', '([^']*)', '([^']*)'\\)"
        );
        Matcher matcher = pattern.matcher(stdout);

        while (matcher.find()) {
            String testCaseId = matcher.group(1);
            Boolean result = Boolean.parseBoolean(matcher.group(2));
            String argument = matcher.group(3);
            String expected = matcher.group(4);
            String actual = matcher.group(5);

            String testCaseName = Objects.requireNonNull(testCaseRepository.findTestCasesByID(UUID.fromString(testCaseId))
                            .orElse(null))
                            .getName();

            records.add(new SubmissionEvaluationRecord(
                    testCaseId,
                    testCaseName,
                    result,
                    argument,
                    expected,
                    actual
            ));
        }

        return records;
    }


}

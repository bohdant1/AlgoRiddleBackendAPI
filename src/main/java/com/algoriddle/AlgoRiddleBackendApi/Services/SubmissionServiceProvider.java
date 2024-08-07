package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.Converters.QuestionConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.TestCase.TestCaseResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SubmissionServiceProvider implements SubmissionService{
    private final QuestionRepository questionRepo;

    @Override
    public String submitQuestion(SubmissionRequestDTO submissionRequestDTO) {
        //1. Generate a unique submission id
        UUID submissionID = UUID.randomUUID();

        //2. Generate an executable
        String executable = generateExecutable(submissionRequestDTO, submissionID);

        //3. Send the executable to judge0

        //4. Await the result

        //5. Interpret the result

        //6. Persist the result

        //7. Return the TestCaseResponseDTO
        return executable;



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

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

@Service
@AllArgsConstructor
public class SubmissionServiceProvider implements SubmissionService{
    private final QuestionRepository questionRepo;

    @Override
    public TestCaseResponseDTO submitQuestion(SubmissionRequestDTO submissionRequestDTO) {
        //1. Check for print keywords in the code
        if (containsPrintPhrase(submissionRequestDTO.sourceCode))
            return null;
        //2. Generate an executable
        generateExecutable(submissionRequestDTO);

        //3. Send the executable to judge0

        //4. Await the result

        //5. Interpret the result

        //6. Return the TestCaseResponseDTO
        return null;



    }

    private String generateExecutable(SubmissionRequestDTO submissionRequestDTO){
        Question question = this.questionRepo
                .findQuestionByID(submissionRequestDTO.getQuestionID())
                .orElse(null);

        // Append the (solution) code with all the test cases
        StringBuilder executable = new StringBuilder(submissionRequestDTO.sourceCode + "\n");

        for(TestCase testCase : question.testCases){
            executable.append(testCase.code)
                    .append("\n");
        }

        // Add the evaluation in the bottom. Run every test case.
        StringBuilder evaluation = new StringBuilder();

        for(TestCase testCase : question.testCases){
            evaluation.append("print(")
                    .append("\""+testCase.getID()+"\"")
                    .append(",")
                    .append("\""+testCase.getMethodName()+"\"")
                    .append(",")
                    .append(testCase.getMethodName())
                    .append("())")
                    .append("\n");
        }

        executable.append(evaluation);

        // Return the full executable
        return executable.toString();
    }

    private static boolean containsPrintPhrase(String s) {
        return s.contains("print(");
    }


}

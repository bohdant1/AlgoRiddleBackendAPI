package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.Converters.QuestionConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
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
    public String submitQuestion(SubmissionRequestDTO submissionRequestDTO) {
        Question question = this.questionRepo
                        .findQuestionByID(submissionRequestDTO.getQuestionID())
                        .orElse(null);

        StringBuilder executable = new StringBuilder(submissionRequestDTO.sourceCode + "\n");

        for(TestCase testCase : question.testCases){
            executable.append(testCase.code)
                      .append("\n");
        }

        String evaluation = "def run():";


        //TODO: create a dictionary test_cases
//        for(TestCase testCase : question.testCases){
//            executable.append(testCase.code)
//                    .append("\n");
//        }

        evaluation += "\"for name, test_case in test_cases.items():\\n\\tprint(name, test_case())\"";







        return executable.toString();
    }
}

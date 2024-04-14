package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    QuestionResponseDTO getQuestionByID(UUID id);

    Page<QuestionResponseDTO> getAllQuestions(int page, int size);

    List<QuestionResponseDTO> getAllQuestions();
    QuestionResponseDTO createQuestion(QuestionRequestDTO question);
    QuestionResponseDTO updateQuestion(QuestionRequestDTO question, UUID id);
    void deleteQuestion(UUID id);
}

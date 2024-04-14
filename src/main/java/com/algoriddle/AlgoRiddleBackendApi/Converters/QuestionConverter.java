package com.algoriddle.AlgoRiddleBackendApi.Converters;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import org.springframework.stereotype.Component;

import java.util.List;


public interface QuestionConverter {
    QuestionResponseDTO entityToDTO(Question Question);
    Question dtoToEntity(QuestionRequestDTO Question);
    List<QuestionResponseDTO> entityToDTO(List<Question> Questions);
    List<Question> dtoToEntity(List<QuestionRequestDTO> Questions);
}

package com.algoriddle.AlgoRiddleBackendApi.Converters;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper implements QuestionConverter{
    @Override
    public QuestionResponseDTO entityToDTO(Question Question) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(Question, QuestionResponseDTO.class);
    }

    @Override
    public Question dtoToEntity(QuestionRequestDTO Question) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(Question, Question.class);
    }

    @Override
    public List<QuestionResponseDTO> entityToDTO(List<Question> Questions) {
        return Questions.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<Question> dtoToEntity(List<QuestionRequestDTO> Questions) {
        return Questions.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}

package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.Converters.QuestionConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.QuestionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class QuestionServiceProvider implements QuestionService{
    private final EntityManager entityManager;
    private final QuestionRepository questionRepo;
    private final QuestionConverter questionConverter;

    @Override
    public QuestionResponseDTO getQuestionByID(UUID id) {
        return this.questionRepo.findQuestionByID(id)
                .map(this.questionConverter::entityToDTO)
                .orElse(null);
    }

    @Override
    public Page<QuestionResponseDTO> getAllQuestions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Question> questionPage = questionRepo.findAll(pageable);
        return questionPage.map(questionConverter::entityToDTO);
    }

    @Override
    public List<QuestionResponseDTO> getAllQuestions() {
        return this.questionRepo
                .findAll()
                .stream()
                .map(this.questionConverter::entityToDTO)
                .toList();
    }

    @Override
    public QuestionResponseDTO createQuestion(QuestionRequestDTO questionDTO) {
        Question question = this.questionConverter.dtoToEntity(questionDTO);
        Question saved = this.questionRepo.save(question);
        return this.questionConverter.entityToDTO(saved);
    }

    @Override
    public QuestionResponseDTO updateQuestion(QuestionRequestDTO questionDTO, UUID id) {
        // Convert DTO to entity
        Question question = questionConverter.dtoToEntity(questionDTO);
        question.setID(id);

        // Check if the question exists in the database
        Optional<Question> existingQuestionOptional = questionRepo.findById(question.getID());

        return existingQuestionOptional.map(existingQuestion -> {
            // Merge the updated data into the existing entity
            entityManager.merge(question);

            // Convert the updated question entity to DTO and return
            return questionConverter.entityToDTO(existingQuestion);
        }).orElseThrow(() -> new EntityNotFoundException("Question with ID " + question.getID() + " not found"));
    }

    @Override
    public void deleteQuestion(UUID id) {
        this.questionRepo.deleteById(id);
    }
}

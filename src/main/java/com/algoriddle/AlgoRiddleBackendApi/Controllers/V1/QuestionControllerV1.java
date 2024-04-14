package com.algoriddle.AlgoRiddleBackendApi.Controllers.V1;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:4200",
        "http://algoriddlewebui.localhost:9080/",
        "https://ashy-dune-025dc7903.5.azurestaticapps.net/",
        "https://algoriddle.live"})
@RequestMapping("/api/v1/questions")
public class QuestionControllerV1 {
    private final QuestionService questions;
    private static final Logger logger = LoggerFactory.getLogger(UserControllerV1.class);

    @Autowired
    public QuestionControllerV1(QuestionService questions) {
        this.questions = questions;
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionByID(@PathVariable("id") UUID id) {
        QuestionResponseDTO dto = questions.getQuestionByID(id);
        return dto != null ?
                ResponseEntity.ok().body(dto) :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestions() {
        List<QuestionResponseDTO> questionsList = questions.getAllQuestions();
        return !questionsList.isEmpty() ?
                ResponseEntity.ok().body(questionsList) :
                ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionDTO) {
        QuestionResponseDTO createdQuestion = questions.createQuestion(questionDTO);
        return createdQuestion != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable("id") UUID id, @RequestBody QuestionRequestDTO questionDTO) {
        QuestionResponseDTO updatedQuestion = questions.updateQuestion(questionDTO, id);
        return updatedQuestion != null ?
                ResponseEntity.ok(updatedQuestion) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") UUID id) {
        questions.deleteQuestion(id);
        logger.info("SUCCESS DELETE Question " + id);
        return ResponseEntity.noContent().build();
    }
}

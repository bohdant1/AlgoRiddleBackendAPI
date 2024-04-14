package com.algoriddle.AlgoRiddleBackendApi.Controllers.V1;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:4200",
        "http://algoriddlewebui.localhost:9080/",
        "https://ashy-dune-025dc7903.5.azurestaticapps.net/",
        "https://algoriddle.live"})
@RequestMapping("/api/v1/questions")
public class QuestionControllerV1 {
    private final QuestionService questions;
    Logger logger = LoggerFactory.getLogger(UserControllerV1.class);

    @Autowired
    public QuestionControllerV1(QuestionService questions) {
        this.questions = questions;
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionByID(@PathVariable("id") UUID id) {
        QuestionResponseDTO dto = questions.getQuestionByID(id);

        if(dto != null) {
            logger.info("SUCCESS GET Question By ID " + id);
            return ResponseEntity.ok().body(dto);
        } else {
            logger.warn("FAILED GET Question By ID " + id);
            return ResponseEntity.notFound().build();
        }
    }
}

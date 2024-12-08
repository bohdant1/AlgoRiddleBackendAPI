package com.algoriddle.AlgoRiddleBackendApi.Controllers.V1;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionEvaluation;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionResponseModel;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Services.SubmissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200",
        "http://algoriddlewebui.localhost:9080/",
        "https://ashy-dune-025dc7903.5.azurestaticapps.net/",
        "https://algoriddle.live"})
@RequestMapping("/api/v1/submissions")
public class SubmissionControllerV1 {
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionControllerV1(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping()
    public ResponseEntity<SubmissionEvaluation> createSubmission(@RequestBody SubmissionRequestDTO submissionRequestDTO) throws JsonProcessingException {
        SubmissionEvaluation result = this.submissionService.submitQuestion(submissionRequestDTO);
        return ResponseEntity.ok().body(result);
    }
}

package com.algoriddle.AlgoRiddleBackendApi.Controllers.V1;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200",
        "http://algoriddlewebui.localhost:9080/",
        "https://ashy-dune-025dc7903.5.azurestaticapps.net/",
        "https://algoriddle.live"})
@RequestMapping("/api/v1/submissions")
public class SubmissionControllerV1 {

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createSubmission(@RequestBody SubmissionRequestDTO submissionRequestDTO) {
        return ResponseEntity.notFound().build();
    }
}

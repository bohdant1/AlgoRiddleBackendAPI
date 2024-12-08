package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.TestCase.TestCaseResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SubmissionService {
    public String submitQuestion(SubmissionRequestDTO submissionRequestDTO) throws JsonProcessingException;
}

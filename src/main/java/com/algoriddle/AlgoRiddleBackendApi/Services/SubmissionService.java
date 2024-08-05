package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.TestCase.TestCaseResponseDTO;

public interface SubmissionService {
    public TestCaseResponseDTO submitQuestion(SubmissionRequestDTO submissionRequestDTO);
}

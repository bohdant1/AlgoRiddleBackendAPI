package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionResponseModel;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.TestCase.TestCaseResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SubmissionService {
    public SubmissionResponseModel submitQuestion(SubmissionRequestDTO submissionRequestDTO) throws JsonProcessingException;
}

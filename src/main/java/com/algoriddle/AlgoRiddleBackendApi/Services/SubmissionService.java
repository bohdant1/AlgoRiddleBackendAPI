package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;

public interface SubmissionService {
    public String submitQuestion(SubmissionRequestDTO submissionRequestDTO);
}

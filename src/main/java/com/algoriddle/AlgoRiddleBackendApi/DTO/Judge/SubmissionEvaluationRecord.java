package com.algoriddle.AlgoRiddleBackendApi.DTO.Judge;

import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;

public record SubmissionEvaluationRecord (
    String testCaseId,
    String testCaseName,
    Boolean result,
    String argument,
    String expected,
    String actual
){}

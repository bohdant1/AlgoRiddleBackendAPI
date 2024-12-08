package com.algoriddle.AlgoRiddleBackendApi.DTO.Judge;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public record SubmissionEvaluation (
    String id,
    List<SubmissionEvaluationRecord> testEvaluationRecords,
    String error
    ){}



package com.algoriddle.AlgoRiddleBackendApi.DTO.Submission;

import com.algoriddle.AlgoRiddleBackendApi.DTO.TestCase.TestCaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponseDTO {
    public @Getter @Setter String id;
    public @Getter @Setter List<TestCaseResponseDTO> testCases;
    public @Getter @Setter Boolean result;
}

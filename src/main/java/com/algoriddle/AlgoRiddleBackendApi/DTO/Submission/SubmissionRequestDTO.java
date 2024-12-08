package com.algoriddle.AlgoRiddleBackendApi.DTO.Submission;

import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class SubmissionRequestDTO {
    public @Getter @Setter String sourceCode;
    public @Getter @Setter UUID questionID;
}

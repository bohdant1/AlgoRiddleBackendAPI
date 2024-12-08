package com.algoriddle.AlgoRiddleBackendApi.DTO.Question;

import com.algoriddle.AlgoRiddleBackendApi.Entity.QuestionDifficulty;
import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDTO {
    @JsonProperty("id")
    public @Getter @Setter UUID ID;
    public @Getter @Setter Integer number;
    public @Getter @Setter String name;
    public @Getter @Setter String description;
    public @Getter @Setter QuestionDifficulty difficulty;
    public @Getter @Setter Set<TestCase> testCases;
}

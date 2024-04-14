package com.algoriddle.AlgoRiddleBackendApi.DTO.Question;

import com.algoriddle.AlgoRiddleBackendApi.Entity.QuestionDifficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO  {
    public @Getter @Setter Integer number;
    public @Getter @Setter String name;
    public @Getter @Setter String description;
    public @Getter @Setter QuestionDifficulty difficulty;
}

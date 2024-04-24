package com.algoriddle.AlgoRiddleBackendApi.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @Column(name = "Question_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public @Getter @Setter UUID ID;
    public @Getter @Setter Integer number;
    public @Getter @Setter String name;
    public @Getter @Setter String description;
    @Enumerated(EnumType.STRING)
    public @Getter @Setter QuestionDifficulty difficulty;

    public Question(Integer number, String name, String description, QuestionDifficulty difficulty) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }
}

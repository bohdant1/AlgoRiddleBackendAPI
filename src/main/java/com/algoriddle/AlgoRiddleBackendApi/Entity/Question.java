package com.algoriddle.AlgoRiddleBackendApi.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @Column(name = "question_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public @Getter @Setter UUID ID;
    public @Getter @Setter Integer number;
    public @Getter @Setter String name;
    public @Getter @Setter String description;
    @Enumerated(EnumType.STRING)
    public @Getter @Setter QuestionDifficulty difficulty;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    public Set<TestCase> testCases;

    public Question(Integer number, String name, String description, QuestionDifficulty difficulty) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.testCases = new HashSet<TestCase>();
    }

    @Override
    public String toString(){
        return this.description;
    }
}

package com.algoriddle.AlgoRiddleBackendApi.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TestCase {
    @Id
    @Column(name = "testCase_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public @Getter @Setter UUID ID;
    public @Getter @Setter String name;
    @Lob
    public @Getter @Setter String code;
}

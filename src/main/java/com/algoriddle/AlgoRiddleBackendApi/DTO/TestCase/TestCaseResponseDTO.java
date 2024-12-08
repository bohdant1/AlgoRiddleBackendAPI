package com.algoriddle.AlgoRiddleBackendApi.DTO.TestCase;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class TestCaseResponseDTO {
    public @Getter @Setter UUID ID;
    public @Getter @Setter String name;
    public @Getter @Setter String output;
    public @Getter @Setter Boolean result;
}

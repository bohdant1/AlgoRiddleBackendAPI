package com.algoriddle.AlgoRiddleBackendApi.DTO.Judge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubmissionResponseModel {

    @JsonProperty("stdout")
    private String stdout;

    @JsonProperty("time")
    private String time;

    @JsonProperty("memory")
    private int memory;

    @JsonProperty("stderr")
    private String stderr; // This can be null.

    @JsonProperty("token")
    private String token;

    @JsonProperty("compile_output")
    private String compileOutput; // This can be null.

    @JsonProperty("message")
    private String message; // This can be null.

    @JsonProperty("status")
    private Status status; // Nested Status object.

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Status {

        @JsonProperty("id")
        private int id;

        @JsonProperty("description")
        private String description;
    }
}

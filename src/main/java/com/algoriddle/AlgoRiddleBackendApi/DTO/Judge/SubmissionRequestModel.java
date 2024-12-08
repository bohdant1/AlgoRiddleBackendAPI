package com.algoriddle.AlgoRiddleBackendApi.DTO.Judge;


public class SubmissionRequestModel {
    public String source_code;
    public Integer language_id;
    public String stdin;

    public SubmissionRequestModel(String source_code) {
        this.source_code = source_code;
        this.language_id = 71;
        this.stdin = "1";
    }
}

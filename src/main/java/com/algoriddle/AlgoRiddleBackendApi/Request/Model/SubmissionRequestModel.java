package com.algoriddle.AlgoRiddleBackendApi.Request.Model;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

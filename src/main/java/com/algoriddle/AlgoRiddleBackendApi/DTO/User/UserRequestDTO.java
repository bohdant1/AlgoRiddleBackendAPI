package com.algoriddle.AlgoRiddleBackendApi.DTO.User;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class UserRequestDTO {
    private @Getter @Setter String name;
    private @Getter @Setter String email;
    private @Getter @Setter String username;

    public UserRequestDTO(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public UserRequestDTO() {}
}

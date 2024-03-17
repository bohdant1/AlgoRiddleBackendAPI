package com.algoriddle.AlgoRiddleBackendApi.DTO.User;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class UserRequestDTO  implements Serializable {
    private @Getter @Setter String email;
    private @Getter @Setter String username;

    public UserRequestDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public UserRequestDTO() {}
}

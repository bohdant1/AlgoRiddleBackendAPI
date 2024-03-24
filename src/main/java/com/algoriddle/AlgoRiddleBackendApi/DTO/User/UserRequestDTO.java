package com.algoriddle.AlgoRiddleBackendApi.DTO.User;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class UserRequestDTO  implements Serializable {
    private @Getter @Setter String email;
    private @Getter @Setter String username;
    private @Getter @Setter Role role;

    public UserRequestDTO(String email, String username, Role role) {
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public UserRequestDTO() {}
}

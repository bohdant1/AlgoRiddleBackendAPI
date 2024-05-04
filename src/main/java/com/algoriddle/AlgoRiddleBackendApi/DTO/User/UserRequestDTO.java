package com.algoriddle.AlgoRiddleBackendApi.DTO.User;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor
public class UserRequestDTO  implements Serializable {
    private @Getter @Setter String email;
    private @Getter @Setter String username;
    private @Getter @Setter String password;
    @Enumerated(EnumType.STRING)
    private @Getter @Setter Role role;


    public UserRequestDTO(String email, String username, Role role) {
        this.email = email;
        this.username = username;
        this.role = role;
    }
}

package com.algoriddle.AlgoRiddleBackendApi.DTO.User;
import com.algoriddle.AlgoRiddleBackendApi.Controllers.UserController;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class UserResponseDTO {
    private @Getter @Setter UUID id;
    private @Getter @Setter String name;
    private @Getter @Setter String email;
    private @Getter @Setter String username;

    public UserResponseDTO(String name, String email, String username) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.username = username;
    }
}

package com.algoriddle.AlgoRiddleBackendApi.DTO.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class UserResponseDTO {
    private @Getter @Setter UUID id;
    private @Getter @Setter String email;
    private @Getter @Setter String username;

    public UserResponseDTO(UUID id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public UserResponseDTO() {}
}

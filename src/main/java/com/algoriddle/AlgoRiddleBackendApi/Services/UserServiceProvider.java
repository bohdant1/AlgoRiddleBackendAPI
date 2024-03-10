package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceProvider implements UserService {

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return new UserResponseDTO(
                "bohdan tymofieienko",
                email,
                "bohdan");
    }
}

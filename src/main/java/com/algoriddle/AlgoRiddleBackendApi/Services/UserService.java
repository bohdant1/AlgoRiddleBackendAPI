package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUserByEmail(String email);
    UserResponseDTO createUser(UserRequestDTO userDTO);
}

package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUserByEmail(String email);
}

package com.algoriddle.AlgoRiddleBackendApi.Converters;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;

import java.util.List;

public interface UserConverter {
    UserResponseDTO entityToDTO(AppUser user);
    AppUser dtoToEntity(UserRequestDTO user);
    List<UserResponseDTO> entityToDTO(List<AppUser> users);
    List<AppUser> dtoToEntity(List<UserRequestDTO> users);
}

package com.algoriddle.AlgoRiddleBackendApi.Converters;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;

import java.util.List;

public interface UserConverter {
    UserResponseDTO EntityToDTO(AppUser user);
    AppUser DTOtoEntity(UserRequestDTO user);
    List<UserResponseDTO> EntityToDTO(List<AppUser> users);
    List<AppUser> DTOtoEntity(List<UserRequestDTO> users);
}

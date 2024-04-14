package com.algoriddle.AlgoRiddleBackendApi.Converters;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements UserConverter{

    @Override
    public UserResponseDTO entityToDTO(AppUser user) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserResponseDTO.class);
    }

    @Override
    public AppUser dtoToEntity(UserRequestDTO user) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, AppUser.class);
    }

    @Override
    public List<UserResponseDTO> entityToDTO(List<AppUser> users) {
        return users.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppUser> dtoToEntity(List<UserRequestDTO> users) {
        return users.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }
}

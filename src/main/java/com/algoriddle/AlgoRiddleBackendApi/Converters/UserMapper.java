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
    public UserResponseDTO EntityToDTO(AppUser user) {
        ModelMapper mapper = new ModelMapper();
        UserResponseDTO result = mapper.map(user, UserResponseDTO.class);
        return result;
    }

    @Override
    public AppUser DTOtoEntity(UserRequestDTO user) {
        ModelMapper mapper = new ModelMapper();
        AppUser result = mapper.map(user, AppUser.class);
        return result;
    }

    @Override
    public List<UserResponseDTO> EntityToDTO(List<AppUser> users) {
        return users.stream().map(x->EntityToDTO(x)).collect(Collectors.toList());
    }

    @Override
    public List<AppUser> DTOtoEntity(List<UserRequestDTO> users) {
        return users.stream().map(x->DTOtoEntity(x)).collect(Collectors.toList());
    }
}

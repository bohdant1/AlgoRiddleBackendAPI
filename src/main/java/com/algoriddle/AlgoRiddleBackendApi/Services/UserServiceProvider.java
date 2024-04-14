package com.algoriddle.AlgoRiddleBackendApi.Services;


import com.algoriddle.AlgoRiddleBackendApi.Converters.UserConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;

import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.UserRepository;
import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Transactional
@AllArgsConstructor
public class UserServiceProvider implements UserService {
    private final UserRepository usersRepo;
    private final UserConverter userConverter;

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return this.usersRepo.findAppUserByEmail(email)
                .map(this.userConverter::entityToDTO)
                .orElse(null);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        AppUser user = this.userConverter.dtoToEntity(userDTO);
        if(user!=null){
            this.usersRepo.save(user);
            UserResponseDTO responseDTO = this.getUserByEmail(user.email);
            return responseDTO;
        }
        else return null;
    }
}

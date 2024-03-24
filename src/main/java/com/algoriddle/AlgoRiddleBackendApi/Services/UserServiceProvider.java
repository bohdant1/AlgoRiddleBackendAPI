package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import com.algoriddle.AlgoRiddleBackendApi.Converters.UserConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.DataJPA.UserDataJPA;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Transactional
public class UserServiceProvider implements UserService {
    private final UserRepository usersRepo;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceProvider(UserRepository usersRepo, UserConverter userConverter) {
        this.usersRepo = usersRepo;
        this.userConverter  = userConverter;
        this.usersRepo.save(new AppUser( "bohdan234", "btimofeenko@gmail.com", Role.USER));
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        AppUser user = this.usersRepo.findAppUserByEmail(email).orElse(null);
        if(user!=null){
            UserResponseDTO dto = this.userConverter.EntityToDTO(user);
            return dto;
        }
        else return null;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        AppUser user = this.userConverter.DTOtoEntity(userDTO);
        if(user!=null){
            this.usersRepo.save(user);
            UserResponseDTO responseDTO = this.getUserByEmail(user.email);
            return responseDTO;
        }
        else return null;
    }
}

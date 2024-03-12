package com.algoriddle.AlgoRiddleBackendApi.Services;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.DataJPA.UserDataJPA;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Transactional
public class UserServiceProvider implements UserService {
    private UserDataJPA usersRepo;

    @Autowired
    public UserServiceProvider(UserDataJPA usersRepo) {
        this.usersRepo = usersRepo;
        this.usersRepo.save(new AppUser("bohdan tymofieienko", "bohdan234", "btimofeenko@gmail.com"));
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        AppUser user = this.usersRepo.findAppUserByEmail(email);
        if(user!=null){
            return new UserResponseDTO(user.ID , user.name, user.email, user.username);
        }
        else return null;
    }
}

package com.algoriddle.AlgoRiddleBackendApi.Services;


import com.algoriddle.AlgoRiddleBackendApi.AlgoRiddleBackendApiApplication;
import com.algoriddle.AlgoRiddleBackendApi.Converters.UserConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;

import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.UserRepository;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceProvider implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceProvider.class);
    private final UserRepository usersRepo;
    private final UserConverter userConverter;

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return this.usersRepo.findAppUserByEmail(email)
                .map(this.userConverter::entityToDTO)
                .orElse(null);
    }



    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO userDTO, boolean addToFirebase)  {
        // Step 1: Add user to Firebase Authentication
        boolean firebaseSuccess = false;

        if(addToFirebase){
            try {
                firebaseSuccess = addUserToFirebase(userDTO);
            } catch (FirebaseAuthException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            firebaseSuccess = true;
        }

        // Step 2: If Firebase addition successful, add user to PostgreSQL
        if (firebaseSuccess) {
            // Logic to add user to PostgreSQL database using JPA
            AppUser user = this.userConverter.dtoToEntity(userDTO);
            this.usersRepo.save(user);

            logger.info("SUCCESS CREATED NEW USER: DATABASE " + userDTO.getEmail());

            return this.getUserByEmail(user.email);
        }
        return null;
    }

    private boolean addUserToFirebase(UserRequestDTO userDTO) throws FirebaseAuthException {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(userDTO.getEmail())
                .setEmailVerified(false)
                .setPassword(userDTO.getPassword())
                .setDisabled(false);
        UserRecord userRecord = firebaseAuth.createUser(request);

        logger.info("SUCCESS CREATED NEW USER: FIREBASE " + userRecord.getEmail());

        return true;
    }
}
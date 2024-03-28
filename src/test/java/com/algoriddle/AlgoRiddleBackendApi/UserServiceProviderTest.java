package com.algoriddle.AlgoRiddleBackendApi;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import com.algoriddle.AlgoRiddleBackendApi.Converters.UserConverter;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.UserRepository;

import com.algoriddle.AlgoRiddleBackendApi.Services.UserServiceProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class UserServiceProviderTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserConverter userConverter;

    @InjectMocks
    private UserServiceProvider userServiceProvider;

    AppUser user = new AppUser("username", "email@mail.com", Role.USER);

    @Test
    public void testGetUserByEmail() {
        // Mocking the behavior of UserRepository
        String email = "email@mail.com";
        when(userRepository.findAppUserByEmail(email)).thenReturn(Optional.of(user));

        String username = "username";
        // Preparing the expected response
        UserResponseDTO expectedResponse = new UserResponseDTO(null,email,username);


        // Calling the method under test
        UserResponseDTO actualResponse = userServiceProvider.getUserByEmail(email);

        // Assertions
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void testCreateUser() {
        // Mocking the behavior of UserConverter
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        AppUser appUser = new AppUser();
        when(userConverter.DTOtoEntity(userRequestDTO)).thenReturn(appUser);

        // Mocking the behavior of UserRepository
        String email = "test@example.com";
        when(userRepository.save(appUser)).thenReturn(appUser);
        when(userRepository.findAppUserByEmail(email)).thenReturn(Optional.of(appUser));

        // Mocking the behavior of UserConverter
        UserResponseDTO expectedResponse = new UserResponseDTO();
        when(userConverter.EntityToDTO(appUser)).thenReturn(expectedResponse);

        // Calling the method under test
        UserResponseDTO actualResponse = userServiceProvider.createUser(userRequestDTO);

        // Assertions
        assertEquals(expectedResponse, actualResponse);
    }
}

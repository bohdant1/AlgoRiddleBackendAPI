package com.algoriddle.AlgoRiddleBackendApi;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import com.algoriddle.AlgoRiddleBackendApi.Converters.UserConverter;
import com.algoriddle.AlgoRiddleBackendApi.Converters.UserMapper;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.UserRepository;

import com.algoriddle.AlgoRiddleBackendApi.Services.UserServiceProvider;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
public class UserServiceProviderTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    UserConverter userConverter = new UserMapper();

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
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());

    }

    @Test
    public void testCreateUser() {
        // Mocking the behavior of UserConverter
        UserRequestDTO userRequestDTO = new UserRequestDTO("email@mail.com","username", Role.USER);
        AppUser appUser = new AppUser("username", "email@mail.com", Role.USER);
        when(userConverter.dtoToEntity(userRequestDTO)).thenReturn(appUser);

        // Mocking the behavior of UserRepository
        when(userRepository.save(appUser)).thenReturn(appUser);
        when(userRepository.findAppUserByEmail("email@mail.com")).thenReturn(Optional.of(appUser));

        // Calling the method under test
        UserResponseDTO actualResponse = userServiceProvider.createUser(userRequestDTO);

        // Assertions
        assertEquals("username", actualResponse.getUsername());
        assertEquals("email@mail.com", actualResponse.getEmail());
        verify(userRepository).save(appUser);
    }
}

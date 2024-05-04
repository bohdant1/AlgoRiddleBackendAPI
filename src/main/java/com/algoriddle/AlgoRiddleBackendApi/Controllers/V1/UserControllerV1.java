package com.algoriddle.AlgoRiddleBackendApi.Controllers.V1;

import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import com.algoriddle.AlgoRiddleBackendApi.Services.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200",
                        "http://algoriddlewebui.localhost:9080/",
                        "https://ashy-dune-025dc7903.5.azurestaticapps.net/",
                        "https://algoriddle.live"})
@RequestMapping("/api/v1/users")
public class UserControllerV1 {
    private final UserService users;
    Logger logger = LoggerFactory.getLogger(UserControllerV1.class);
    @Autowired
    public UserControllerV1(UserService users) {
        this.users = users;
    }

    @GetMapping()
    public ResponseEntity<UserResponseDTO> getUserByEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        String email = user.getEmail(); // Get the email of the authenticated user

        UserResponseDTO dto = users.getUserByEmail(email);
        if(dto != null) {
            logger.info("SUCCESS GET User By Email " + email);
            return ResponseEntity.ok().body(dto);
        } else {
            logger.warn("FAILED GET User By Email " + email);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        UserResponseDTO response = this.users.createUser(userDTO, true);
        return response != null ?
                ResponseEntity.ok().body(response) :
                ResponseEntity.notFound().build();
    }
}

package com.algoriddle.AlgoRiddleBackendApi.Controllers;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    private final UserService users;
    @Autowired
    public UserController(UserService users) {
        this.users = users;
    }

    @GetMapping()
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(users.getUserByEmail(email));
    }
}

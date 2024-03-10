package com.algoriddle.AlgoRiddleBackendApi.Controllers;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {

    @GetMapping()
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok().body(new UserResponseDTO(
                "bohdan tymofieienko",
                email,
                "bohdan"));
    }
}

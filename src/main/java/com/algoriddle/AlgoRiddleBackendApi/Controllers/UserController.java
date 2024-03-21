package com.algoriddle.AlgoRiddleBackendApi.Controllers;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
    private final UserService users;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService users) {
        this.users = users;
    }

    @GetMapping()
    public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam("email") String email) {
        UserResponseDTO dto = users.getUserByEmail(email);
        if(dto!=null){
            logger.info("SUCCESS GET User By Email " + email);
            return ResponseEntity.ok().body(dto);
        }
        else{
            logger.info("FAILED GET User By Email " + email);
            return ResponseEntity.notFound().build();
        }
    }
}

package com.algoriddle.AlgoRiddleBackendApi.Controllers;

import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserResponseDTO;
import com.algoriddle.AlgoRiddleBackendApi.Security.Model.FirebaseAuthenticationToken;
import com.algoriddle.AlgoRiddleBackendApi.Services.UserService;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    //dummy controller to test out the idea with SecurityContextHolder
    @GetMapping("/dummy")
    public ResponseEntity<String> getDummy() {
        // Retrieve the authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof FirebaseAuthenticationToken) {
            // Cast the authentication object to FirebaseAuthenticationToken
            FirebaseAuthenticationToken firebaseAuthentication = (FirebaseAuthenticationToken) authentication;

            // Retrieve the principal, which should be the decoded Firebase token
            FirebaseToken decodedToken = (FirebaseToken) firebaseAuthentication.getPrincipal();

            // Access the data from the decoded token
            String uid = decodedToken.getUid();
            // You can access more data depending on your token structure
            // For example, decodedToken.getClaims() to get custom claims

            // Do something with the data
            return ResponseEntity.ok("UID: " + uid);
        }
        return ResponseEntity.notFound().build();
    }
}

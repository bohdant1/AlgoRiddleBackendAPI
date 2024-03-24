package com.algoriddle.AlgoRiddleBackendApi.Security;

import com.algoriddle.AlgoRiddleBackendApi.DataJPA.UserDataJPA;
import com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService(){
        return email -> repository.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

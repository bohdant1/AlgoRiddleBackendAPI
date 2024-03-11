package com.algoriddle.AlgoRiddleBackendApi.DataJPA;

import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDataJPA extends JpaRepository<AppUser, UUID> {
    AppUser findAppUserByEmail(String Email);
    AppUser findAppUserByID(UUID ID);
}

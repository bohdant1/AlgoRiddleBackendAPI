package com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA;

import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findAppUserByEmail(String email);

}

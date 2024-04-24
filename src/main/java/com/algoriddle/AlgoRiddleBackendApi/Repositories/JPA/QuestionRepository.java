package com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA;

import com.algoriddle.AlgoRiddleBackendApi.Entity.AppUser;
import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> findQuestionByID(UUID uuid);
}

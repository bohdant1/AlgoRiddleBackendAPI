package com.algoriddle.AlgoRiddleBackendApi.Repositories.JPA;

import com.algoriddle.AlgoRiddleBackendApi.Entity.Question;
import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TestCaseRepository extends JpaRepository<TestCase, UUID> {
    Optional<TestCase> findTestCasesByID(UUID uuid);
}

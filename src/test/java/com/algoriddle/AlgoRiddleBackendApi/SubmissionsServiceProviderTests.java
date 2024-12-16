package com.algoriddle.AlgoRiddleBackendApi;


import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionEvaluation;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Judge.SubmissionEvaluationRecord;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Submission.SubmissionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.QuestionDifficulty;
import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;
import com.algoriddle.AlgoRiddleBackendApi.Services.QuestionService;
import com.algoriddle.AlgoRiddleBackendApi.Services.SubmissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class SubmissionsServiceProviderTests {

    @Autowired
    QuestionService questionService;
    @Autowired
    SubmissionService submissionService;

    UUID questionID;

    @BeforeAll
    public void prepareData(){
        Set<TestCase> testCaseSet = new HashSet<>();
        testCaseSet.add(TestCase.builder().name("testcase1").code("def test3():\r\n\targument = \"Maria\"\r\n\tresult = greet(argument)\r\n\texpected = 'Hello Maria'\r\n\tif result == expected: \r\n\t\treturn (True, argument, expected, result)\r\n\treturn (False, argument, expected, result)").build());
        testCaseSet.add(TestCase.builder().name("testcase2").code("def test2():\r\n\targument = \"John\"\r\n\tresult = greet(argument)\r\n\texpected = 'Hello John'\r\n\tif result == expected: \r\n\t\treturn (True, argument, expected, result)\r\n\treturn (False, argument, expected, result)").build());
        testCaseSet.add(TestCase.builder().name("testcase3").code("def test1():\r\n\targument = \"Bohdan\"\r\n\tresult = greet(\"Bohdan\")\r\n\texpected = 'Hello Bohdan'\r\n\tif result == expected: \r\n\t\treturn (True, argument, expected, result)\r\n\treturn (False, argument, expected, result)").build());

        QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO(
                123456,
                "Greetings",
                "Best description",
                QuestionDifficulty.EASY,
                testCaseSet
        );

        var question = questionService.createQuestion(questionRequestDTO);
        this.questionID =question.getID();
    }

    @AfterAll
    public void cleanData(){
        questionService.deleteQuestion(this.questionID);
    }

    @Test
    @Transactional
    public void testFullSubmissionFlowIT() throws JsonProcessingException {
        SubmissionRequestDTO dto = new SubmissionRequestDTO(
                "#Type you solution here\r\ndef greet(name):\r\n\treturn \"Hello \" + name",
                questionID
        );

        SubmissionEvaluation evaluation =  submissionService.submitQuestion(dto);


        SubmissionEvaluationRecord expected_1 = new SubmissionEvaluationRecord(
                UUID.randomUUID().toString(),
                "testcase1",
                true,
                "Maria",
                "Hello Maria",
                "Hello Maria"
        );
        SubmissionEvaluationRecord expected_2 = new SubmissionEvaluationRecord(
                UUID.randomUUID().toString(),
                "testcase2",
                true,
                "John",
                "Hello John",
                "Hello John"
        );
        SubmissionEvaluationRecord expected_3 = new SubmissionEvaluationRecord(
                UUID.randomUUID().toString(),
                "testcase3",
                true,
                "Bohdan",
                "Hello Bohdan",
                "Hello Bohdan"
        );

        List<SubmissionEvaluationRecord> evaluationRecords = evaluation.testEvaluationRecords();
        // Sort based on testCaseName to reduce stochastic
        evaluationRecords.sort(Comparator.comparing(SubmissionEvaluationRecord::testCaseName));
        compareSubmissionEvaluationRecordsWithAssertion(expected_1,evaluationRecords.get(0));
        compareSubmissionEvaluationRecordsWithAssertion(expected_2, evaluationRecords.get(1));
        compareSubmissionEvaluationRecordsWithAssertion(expected_3, evaluationRecords.get(2));
    }

    private void compareSubmissionEvaluationRecordsWithAssertion(SubmissionEvaluationRecord expected,
                                                                 SubmissionEvaluationRecord actual)
    {
        assertEquals(expected.result(), actual.result());
        assertEquals(expected.testCaseName(), actual.testCaseName());
        assertEquals(expected.argument(), actual.argument());
        assertEquals(expected.expected(), actual.expected());
        assertEquals(expected.actual(), actual.actual());

    }
}

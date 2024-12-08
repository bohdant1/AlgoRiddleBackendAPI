package com.algoriddle.AlgoRiddleBackendApi;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.QuestionDifficulty;
import com.algoriddle.AlgoRiddleBackendApi.Entity.TestCase;
import com.algoriddle.AlgoRiddleBackendApi.Services.QuestionService;
import com.algoriddle.AlgoRiddleBackendApi.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AlgoRiddleBackendApiApplication {
	private final Logger logger = LoggerFactory.getLogger(AlgoRiddleBackendApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlgoRiddleBackendApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLinerRunner(
			UserService users,
			QuestionService questions
	) {

		return args -> {
			var admin = users.createUser(new UserRequestDTO(
					"btimofeenko@gmail.com",
					"bohdan234",
					Role.ADMIN
			), false);

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

			var question = questions.createQuestion(questionRequestDTO);
		};
	}

}

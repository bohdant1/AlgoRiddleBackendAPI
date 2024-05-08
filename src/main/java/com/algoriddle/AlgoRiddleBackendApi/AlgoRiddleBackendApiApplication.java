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
			testCaseSet.add(TestCase.builder().name("testcase1").code("def test1():\n\tresult = greet(\"Bohdan\")\n\texpected = 'Hello Bohdan'\n\tif result == expected: \n\t\treturn True\n\treturn (False, result)").build());
			testCaseSet.add(TestCase.builder().name("testcase2").code("def test2():\n\tresult = greet(\"John\")\n\texpected = 'Hello John1'\n\tif result == expected: \n\t\treturn True\n\treturn (False, result)").build());
			testCaseSet.add(TestCase.builder().name("testcase3").code("def test3():\n\tresult = greet(\"Maria\")\n\texpected = 'Hello Maria'\n\tif result == expected: \n\t\treturn True\n\treturn (False, result)").build());

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

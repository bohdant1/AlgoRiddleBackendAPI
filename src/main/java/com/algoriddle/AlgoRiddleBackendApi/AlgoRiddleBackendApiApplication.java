package com.algoriddle.AlgoRiddleBackendApi;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import com.algoriddle.AlgoRiddleBackendApi.DTO.Question.QuestionRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
import com.algoriddle.AlgoRiddleBackendApi.Entity.QuestionDifficulty;
import com.algoriddle.AlgoRiddleBackendApi.Services.QuestionService;
import com.algoriddle.AlgoRiddleBackendApi.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

			var question = questions.createQuestion(new QuestionRequestDTO(
					123456,
					"Two Sums",
					"Best description",
					QuestionDifficulty.EASY
			));
		};
	}

}

package com.algoriddle.AlgoRiddleBackendApi;

import com.algoriddle.AlgoRiddleBackendApi.Access.Role;
import com.algoriddle.AlgoRiddleBackendApi.Controllers.UserController;
import com.algoriddle.AlgoRiddleBackendApi.DTO.User.UserRequestDTO;
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
			UserService service
	) {

		return args -> {
			var admin = service.createUser(new UserRequestDTO(
					"btimofeenko@gmail.com",
					"bohdan234",
					Role.ADMIN
			));
			logger.info("SUCCESS Created new User " + admin.getEmail());
		};
	}

}

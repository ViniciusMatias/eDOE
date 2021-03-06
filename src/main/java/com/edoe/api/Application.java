package com.edoe.api;

import com.edoe.api.entity.User;
import com.edoe.api.enums.Role;
import com.edoe.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User admin = new User(1L, "admin@admin.com", "12345", "83991625262", "Igreja", "indet", Role.ADMIN);

		userRepository.save(admin);



		User doador = new User(null, "doador@doador.com", "12345", "83991625262", "Igreja", "indet", Role.APENAS_DOADOR);

		userRepository.save(doador);

		User doador2 = new User(null, "doador@doador2.com", "12345", "83991625262", "Igreja", "indet", Role.APENAS_DOADOR);

		userRepository.save(doador2);

		User receptor = new User(null, "receptor@receptor.com", "12345", "83991625262", "Igreja", "indet", Role.APENAS_RECEPTOR);

		userRepository.save(receptor);

	}
}

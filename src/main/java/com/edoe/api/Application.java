package com.edoe.api;

import com.edoe.api.entity.Role;
import com.edoe.api.entity.User;
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
		User user1 = new User(null, "teste@teste123", "8399353038", "Igreja", "teste", null);
		User user2 = new User(null, "teste@teste123", "8399353038", "Igreja", "teste", Role.ADMIN);
		userRepository.save(user1);
		userRepository.save(user2);
	}
}

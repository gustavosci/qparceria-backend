package br.com.qparceria;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.qparceria.domain.User;
import br.com.qparceria.repositories.UserRepository;

@SpringBootApplication
public class QparceriaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo; 
	
	public static void main(String[] args) {
		SpringApplication.run(QparceriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Gustavo Santos", "gustavo", "gustavosci@Live.com", "123456");
		User user2 = new User(null, "Daniela Morais", "dani", "danielamorais@Live.com", "789");
		ArrayList<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		userRepo.saveAll(users);
	}
}

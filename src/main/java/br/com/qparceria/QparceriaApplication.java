package br.com.qparceria;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.qparceria.domain.Sport;
import br.com.qparceria.domain.User;
import br.com.qparceria.repositories.SportRepository;
import br.com.qparceria.repositories.UserRepository;

@SpringBootApplication
public class QparceriaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo; 
	@Autowired
	private SportRepository sportRepo; 
	
	public static void main(String[] args) {
		SpringApplication.run(QparceriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Gustavo Santos", "gustavo", "gustavosci@Live.com", "123456");
		User user2 = new User(null, "Daniela Morais", "dani", "danielamorais@Live.com", "789");
		
		Sport sport1 = new Sport(null, "Corrida");
		Sport sport2 = new Sport(null, "Ciclismo");
		Sport sport3 = new Sport(null, "Caminhada");
		
		user1.getSports().addAll(Arrays.asList(sport1, sport2, sport3));
		user2.getSports().addAll(Arrays.asList(sport2));
		
		sport1.getUsers().addAll(Arrays.asList(user1));
		sport2.getUsers().addAll(Arrays.asList(user1, user2));
		sport3.getUsers().addAll(Arrays.asList(user1));
		
		// Quem está com MAPPEDBY deve ser criado primeiro
		userRepo.saveAll(Arrays.asList(user1, user2));
		sportRepo.saveAll(Arrays.asList(sport1, sport2, sport3));
		
		
	}
}

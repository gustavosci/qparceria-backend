package br.com.qparceria;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.qparceria.domain.City;
import br.com.qparceria.domain.Sport;
import br.com.qparceria.domain.UF;
import br.com.qparceria.domain.User;
import br.com.qparceria.repositories.CityRepository;
import br.com.qparceria.repositories.SportRepository;
import br.com.qparceria.repositories.UFRepository;
import br.com.qparceria.repositories.UserRepository;

@SpringBootApplication
public class QparceriaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo; 
	@Autowired
	private SportRepository sportRepo; 
	@Autowired
	private UFRepository ufRepo; 
	@Autowired
	private CityRepository cityRepo; 
	
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
		
		UF uf1 = new UF(null, "Rio Grande do Sul", "RS");
		UF uf2 = new UF(null, "São Paulo", "SP");
		
		City city1 = new City(null, "Porto Alegre", uf1);
		City city2 = new City(null, "Campinas", uf2);
		City city3 = new City(null, "Sapiranga", uf1);

		uf1.getCities().addAll(Arrays.asList(city1, city3));
		uf2.getCities().addAll(Arrays.asList(city2));
		
		ufRepo.saveAll(Arrays.asList(uf1, uf2));
		cityRepo.saveAll(Arrays.asList(city1, city2, city3));

	}
}

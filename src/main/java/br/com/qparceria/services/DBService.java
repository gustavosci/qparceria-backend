package br.com.qparceria.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.domain.Adress;
import br.com.qparceria.domain.City;
import br.com.qparceria.domain.Mate;
import br.com.qparceria.domain.Sport;
import br.com.qparceria.domain.UF;
import br.com.qparceria.domain.User;
import br.com.qparceria.domain.enuns.Frequency;
import br.com.qparceria.domain.enuns.Gender;
import br.com.qparceria.domain.enuns.WeekDays;
import br.com.qparceria.repositories.ActivityRepository;
import br.com.qparceria.repositories.AdressRepository;
import br.com.qparceria.repositories.CityRepository;
import br.com.qparceria.repositories.MatchRepository;
import br.com.qparceria.repositories.SportRepository;
import br.com.qparceria.repositories.UFRepository;
import br.com.qparceria.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository userRepo; 
	@Autowired
	private SportRepository sportRepo; 
	@Autowired
	private UFRepository ufRepo; 
	@Autowired
	private CityRepository cityRepo; 
	@Autowired
	private AdressRepository adressRepo; 
	@Autowired
	private ActivityRepository activityRepo; 
	@Autowired
	private MatchRepository matchRepo; 

	
	public void instantiateTestDataBase() {
		UF uf1 = new UF(null, "Rio Grande do Sul", "RS");
		UF uf2 = new UF(null, "São Paulo", "SP");
		
		City city1 = new City(null, "Porto Alegre", uf1);
		City city2 = new City(null, "Campinas", uf2);
		City city3 = new City(null, "Sapiranga", uf1);

		uf1.getCities().addAll(Arrays.asList(city1, city3));
		uf2.getCities().addAll(Arrays.asList(city2));
		
		ufRepo.saveAll(Arrays.asList(uf1, uf2));
		cityRepo.saveAll(Arrays.asList(city1, city2, city3));
		
		Adress ad1 = new Adress(null, "Barão do Rio Branco", "463", "", "Santa Fé", 93800000, city3);
		Adress ad2 = new Adress(null, "Teste Adress 2", "999", "Nao tem", "Centro", 93600000, city2);
		adressRepo.saveAll(Arrays.asList(ad1, ad2));
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

		User user1 = new User(null, "Gustavo Santos", "gustavo", "gustavosci@Live.com", 
							  pe.encode("123456"), Gender.MASCULINO, LocalDate.parse("27/10/1992", dateFormatter), ad1);
		User user2 = new User(null, "Daniela Morais", "dani", "danielamorais@Live.com", 
							  pe.encode("123"), Gender.FEMININO, LocalDate.parse("13/11/1994", dateFormatter), ad2);
		
		Sport sport1 = new Sport(null, "Corrida");
		Sport sport2 = new Sport(null, "Ciclismo");
		Sport sport3 = new Sport(null, "Caminhada");
		
		user1.getSports().addAll(Arrays.asList(sport1, sport2, sport3));
		user2.getSports().addAll(Arrays.asList(sport2));
		user1.getPhones().addAll(Arrays.asList("51996032728"));
		
		sport1.getUsers().addAll(Arrays.asList(user1));
		sport2.getUsers().addAll(Arrays.asList(user1, user2));
		sport3.getUsers().addAll(Arrays.asList(user1));
		
		// Quem está com MAPPEDBY deve ser criado primeiro
		sportRepo.saveAll(Arrays.asList(sport1, sport2, sport3));
		userRepo.saveAll(Arrays.asList(user1, user2));
		
		Activity act1 = new Activity("Posto Ipiranga", "Tintas Santos", city1, city1, "Estrada", "Banda da hora", 
				LocalTime.parse("15:23:00", timeFormatter), false, true, true, true, true, true, false, 
				new BigDecimal("80.80").setScale(2, RoundingMode.HALF_EVEN), 
				new BigDecimal("150.89").setScale(2, RoundingMode.HALF_EVEN), 
				new BigDecimal("78.90").setScale(2, RoundingMode.HALF_EVEN),
				3, Frequency.SPECIFIC_DATE, LocalDate.parse("10/05/2018", dateFormatter), 
				true, sport2, user1);
		Activity act2 = new Activity("Centro", "Amaral", city3, city1, "Barro e terra", "Rota do café", 
				LocalTime.parse("18:23:19", timeFormatter), true, false, true, true, false, false, true, 
				new BigDecimal("80.80").setScale(2, RoundingMode.HALF_EVEN), 
				new BigDecimal("150.89").setScale(2, RoundingMode.HALF_EVEN),
				new BigDecimal("78.90").setScale(2, RoundingMode.HALF_EVEN),
				10, Frequency.REGULAR, null, true, sport1, user2);
		Set<WeekDays> days = new HashSet<>();	
		days.add(WeekDays.MONDAY);
		days.add(WeekDays.THURSDAY);
		days.add(WeekDays.SUNDAY);
		act2.setDays(days);
		activityRepo.saveAll(Arrays.asList(act1, act2));
		
		Mate m1 = new Mate(act1, user2, LocalDate.parse("03/06/2018", dateFormatter));
		Mate m2 = new Mate(act2, user1, LocalDate.parse("10/06/2018", dateFormatter));		
		act1.getMatches().addAll(Arrays.asList(m1));
		act2.getMatches().addAll(Arrays.asList(m2));		
		user1.getMatches().addAll(Arrays.asList(m2));
		user2.getMatches().addAll(Arrays.asList(m1));
		matchRepo.saveAll(Arrays.asList(m1, m2));		
	}
}

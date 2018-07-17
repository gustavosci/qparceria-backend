package br.com.qparceria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QparceriaApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(QparceriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
	}
}

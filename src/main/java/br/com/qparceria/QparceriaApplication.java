package br.com.qparceria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.qparceria.services.S3Service;

@SpringBootApplication
public class QparceriaApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(QparceriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		s3Service.uploadFile("C:\\Users\\Gustavo\\Desktop\\07-imagens-com-amazon-s3.pdf");
	}
}

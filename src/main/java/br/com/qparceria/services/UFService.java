package br.com.qparceria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.UF;
import br.com.qparceria.repositories.UFRepository;

@Service
public class UFService {

	@Autowired
	private UFRepository ufRepo;

	public List<UF> findAll() {
		return ufRepo.findAllByOrderBySigla();
	}

}

package br.com.qparceria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.Sport;
import br.com.qparceria.repositories.SportRepository;

@Service
public class SportService {

	@Autowired
	private SportRepository sportRepo;

	public List<Sport> findAll() {
		return sportRepo.findAllByOrderByName();
	}

}

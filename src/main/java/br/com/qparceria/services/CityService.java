package br.com.qparceria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.City;
import br.com.qparceria.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo; 

	public List<City> findByUF(Integer ufId) {
		return cityRepo.findCities(ufId);
	}

}

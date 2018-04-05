package br.com.qparceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qparceria.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}

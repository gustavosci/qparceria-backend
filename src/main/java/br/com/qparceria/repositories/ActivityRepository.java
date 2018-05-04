package br.com.qparceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qparceria.domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>{
	
}

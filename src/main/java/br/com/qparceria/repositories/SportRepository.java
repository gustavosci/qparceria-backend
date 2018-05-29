package br.com.qparceria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Integer>{

	@Transactional(readOnly=true)
	List<Sport> findAllByOrderByName();
}

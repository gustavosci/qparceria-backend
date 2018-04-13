package br.com.qparceria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.UF;

@Repository
public interface UFRepository extends JpaRepository<UF, Integer>{

	@Transactional(readOnly=true)
	UF findAllByOrderByName();

	@Transactional(readOnly=true)
	List<UF> findAllByOrderBySigla();

}

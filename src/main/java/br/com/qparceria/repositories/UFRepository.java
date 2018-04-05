package br.com.qparceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qparceria.domain.UF;

@Repository
public interface UFRepository extends JpaRepository<UF, Integer>{

}

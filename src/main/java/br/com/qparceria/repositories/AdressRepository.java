package br.com.qparceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qparceria.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer>{

}

package br.com.qparceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qparceria.domain.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer>{

}

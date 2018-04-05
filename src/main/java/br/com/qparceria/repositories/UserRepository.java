package br.com.qparceria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.qparceria.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}

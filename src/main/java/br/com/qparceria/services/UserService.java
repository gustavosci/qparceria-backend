package br.com.qparceria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.User;
import br.com.qparceria.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User find(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElse(null);
	}
}

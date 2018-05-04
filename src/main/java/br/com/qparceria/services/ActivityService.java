package br.com.qparceria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.repositories.ActivityRepository;
import br.com.qparceria.security.UserSS;
import br.com.qparceria.services.exceptions.AuthorizationException;
import br.com.qparceria.services.exceptions.ObjectNotFoundException;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository repo;

	public Activity find(Integer id) {		
		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Activity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Activity.class.getName()));				
	}

	public List<Activity> findAll() {
		return repo.findAll();
	}

}

package br.com.qparceria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.Mate;
import br.com.qparceria.repositories.MatchRepository;
import br.com.qparceria.security.UserSS;
import br.com.qparceria.services.exceptions.AuthorizationException;

@Service
public class MatchService {

	@Autowired
	private MatchRepository matchRepo; 
	
	public List<Mate> findAllProducedOfUserLogged() {
		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado");
		}		
		return matchRepo.findAllProducedOfUser(userSS.getId());
	}

	public List<Mate> findAllReceivedOfUserLogged() {
		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado");
		}		
		return matchRepo.findAllReceivedOfUser(userSS.getId());
	}
	
}

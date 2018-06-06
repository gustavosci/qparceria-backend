package br.com.qparceria.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.domain.Mate;
import br.com.qparceria.domain.User;
import br.com.qparceria.repositories.MatchRepository;
import br.com.qparceria.repositories.UserRepository;
import br.com.qparceria.security.UserSS;
import br.com.qparceria.services.exceptions.AuthorizationException;
import br.com.qparceria.services.exceptions.DataIntegrityException;
import br.com.qparceria.services.exceptions.ObjectNotFoundException;

@Service
public class MatchService {

	@Autowired
	private ActivityService actService;

	@Autowired
	private UserRepository userRepo;	
	@Autowired
	private MatchRepository matchRepo;
	
	public Mate insert(Integer id) {
		Activity act = actService.find(id);
		UserSS userSS = UserLoggedService.authenticated();
		if(userSS.getId() == act.getOwner().getId()) {
			throw new DataIntegrityException("Não é possível realizar match em atividade própria");
		}
		for(Mate m : act.getMatches()) {
			if(m.getUser().getId() == userSS.getId()) {
				throw new DataIntegrityException("Usuário já realizou match na atividade");
			}
		}
		Mate match = new Mate(act, loadUser(userSS.getId()), LocalDate.now());
		return matchRepo.save(match);
	}

	public void delete(Integer id) {
		Activity act = actService.find(id);
		try {
			Mate m = getUserMatchOnActivity(act); 
			if( m != null) {
				matchRepo.delete(m);
			} else {
				throw new DataIntegrityException("Não existe match do usuário para a atividade " + id);
			}
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir o match da atividade " + id);
		}		
	}
	
	private Mate getUserMatchOnActivity(Activity act) {
		UserSS userSS = UserLoggedService.authenticated();
		for(Mate m : act.getMatches()) {
			if(m.getUser().getId() == userSS.getId()) {
				return m;
			}
		}				
		return null;
	}
		
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
	
	private User loadUser(Integer id) {
		Optional<User> userOpt = userRepo.findById(id);
		return userOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));		
	}	
}

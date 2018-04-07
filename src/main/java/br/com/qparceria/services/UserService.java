package br.com.qparceria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.User;
import br.com.qparceria.repositories.UserRepository;
import br.com.qparceria.services.exceptions.DataIntegrityException;
import br.com.qparceria.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User find(Integer id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));				
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public User insert(User obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public User update(User obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir um usuário que possui relacionamentos");
		}		
	}
}

package br.com.qparceria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.qparceria.domain.User;
import br.com.qparceria.dto.UserDTO;
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
	
	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
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
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO);
	}
}

package br.com.qparceria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.Adress;
import br.com.qparceria.domain.City;
import br.com.qparceria.domain.Sport;
import br.com.qparceria.domain.User;
import br.com.qparceria.dto.UserSaveDTO;
import br.com.qparceria.repositories.AdressRepository;
import br.com.qparceria.repositories.CityRepository;
import br.com.qparceria.repositories.SportRepository;
import br.com.qparceria.repositories.UserRepository;
import br.com.qparceria.services.exceptions.DataIntegrityException;
import br.com.qparceria.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private AdressRepository adressRepo; 
	@Autowired
	private CityRepository cityRepo; 
	@Autowired
	private SportRepository sportRepo; 

	
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

	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		adressRepo.save(obj.getAdress());
		return repo.save(obj);
	}
	
	public User update(User obj) {
		User newObj = find(obj.getId());
		updateData(newObj, obj);
		adressRepo.save(newObj.getAdress());
		return repo.save(newObj);
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
	
	public User fromSaveDTO(UserSaveDTO objDTO) {
		User user = new User(objDTO); 
		
		Optional<City> cityOpt = cityRepo.findById(objDTO.getCityId());
		City city = cityOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + objDTO.getCityId() + ", Tipo: " + City.class.getName()));				
		
		Adress adress = new Adress(objDTO.getAdressId(), objDTO.getStreet(), 
				objDTO.getNumber(), objDTO.getComplement(), objDTO.getNeighborhood(), objDTO.getCep(), city);		
		user.setAdress(adress);
		
		// Ajustar
		user.getSports().clear();
		for (Integer s : objDTO.getSports()) {
			System.out.println("Sport lido" + " " + s);
			Optional<Sport> sportOpt = sportRepo.findById(s);
			Sport sport = sportOpt.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + s + ", Tipo: " + Sport.class.getName()));
			user.getSports().add(sport);			
		}

		user.getPhones().clear();
		if (objDTO.getPhone() != null) {
			user.getPhones().add(objDTO.getPhone());
		}
		if (objDTO.getPhone2() != null) {
			user.getPhones().add(objDTO.getPhone2());
		}
		if (objDTO.getPhone3() != null) {
			user.getPhones().add(objDTO.getPhone3());
		}		
		
		return user;
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setUsername(obj.getUsername());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
		newObj.setGender(obj.getGender());
		newObj.setBirthDate(obj.getBirthDate());
		newObj.setFacebook(obj.getFacebook());
		newObj.setTwitter(obj.getTwitter());
		newObj.setInstagram(obj.getInstagram());
		newObj.setStrava(obj.getStrava());
		newObj.setPic(obj.getPic());
		newObj.setAdress(obj.getAdress());
		newObj.setPhones(obj.getPhones());
		newObj.setSports(obj.getSports());
	}
}

package br.com.qparceria.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.qparceria.domain.Adress;
import br.com.qparceria.domain.City;
import br.com.qparceria.domain.Sport;
import br.com.qparceria.domain.User;
import br.com.qparceria.domain.enuns.Profile;
import br.com.qparceria.dto.UserDTO;
import br.com.qparceria.repositories.AdressRepository;
import br.com.qparceria.repositories.CityRepository;
import br.com.qparceria.repositories.SportRepository;
import br.com.qparceria.repositories.UserRepository;
import br.com.qparceria.security.UserSS;
import br.com.qparceria.services.exceptions.AuthorizationException;
import br.com.qparceria.services.exceptions.DataIntegrityException;
import br.com.qparceria.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private AdressRepository adressRepo; 
	
	@Autowired
	private CityRepository cityRepo; 
	
	@Autowired
	private SportRepository sportRepo;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.user.profile}")
	private String imgPrefixUserProfile;

	@Value("${img.ext.user.profile}")
	private String imgExtUserProfile;

	public User find(Integer id) {		
		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null || !userSS.hasHole(Profile.ADMIN) && !id.equals(userSS.getId()) ) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));				
	}

	public List<User> findAll() {
		return repo.findAll();
	}
	
	public UserDTO findByUsername(String username) {		
		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null || !userSS.hasHole(Profile.ADMIN) && !username.equals(userSS.getUsername()) ) {
			throw new AuthorizationException("Acesso negado");
		}
		User obj = repo.findByUsername(username);
		if (obj == null) {
			new ObjectNotFoundException("Objeto não encontrado! Username: " + username + ", Tipo: " + User.class.getName());
		}		
		
		return toDTO(obj); 				
	}
	
	private UserDTO toDTO(User obj) {
		return new UserDTO(obj);
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
	
	public User fromDTO(UserDTO objDTO) {
		User user = new User(objDTO);
		user.setPassword(pe.encode(objDTO.getPassword()));
				
		Adress adress = new Adress(objDTO.getAdressId(), objDTO.getStreet(), 
								   objDTO.getNumber(), objDTO.getComplement(), 
								   objDTO.getNeighborhood(), objDTO.getCep(), 
								   loadCity(objDTO.getCityId()));		
		user.setAdress(adress);
		
		user.getSports().clear();
		if (objDTO.isRun()) {
			user.getSports().add(loadSport(1));
		}
		if (objDTO.isCyclism()) {
			user.getSports().add(loadSport(2));
		}
		if (objDTO.isWalk()) {
			user.getSports().add(loadSport(3));
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

	private City loadCity(Integer id) {
		Optional<City> cityOpt = cityRepo.findById(id);
		return cityOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + City.class.getName()));				
	}
	
	private Sport loadSport(Integer id) {
		Optional<Sport> sportOpt = sportRepo.findById(id);
		return sportOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Sport.class.getName()));		
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setUsername(obj.getUsername());
		newObj.setEmail(obj.getEmail());
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
	
	public URI uploadPicUser(MultipartFile file) {
		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImagemFromMultipartFile(file);
		String fileName = imgPrefixUserProfile + userSS.getId() +  imgExtUserProfile;
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage,  imgExtUserProfile), fileName, "image");
	}
		
}

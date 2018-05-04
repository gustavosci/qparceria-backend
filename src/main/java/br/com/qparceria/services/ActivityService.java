package br.com.qparceria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.domain.City;
import br.com.qparceria.domain.Sport;
import br.com.qparceria.domain.User;
import br.com.qparceria.domain.enuns.WeekDays;
import br.com.qparceria.dto.ActivityDTO;
import br.com.qparceria.repositories.ActivityRepository;
import br.com.qparceria.repositories.CityRepository;
import br.com.qparceria.repositories.SportRepository;
import br.com.qparceria.repositories.UserRepository;
import br.com.qparceria.security.UserSS;
import br.com.qparceria.services.exceptions.AuthorizationException;
import br.com.qparceria.services.exceptions.DataIntegrityException;
import br.com.qparceria.services.exceptions.ObjectNotFoundException;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository repo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CityRepository cityRepo; 
	@Autowired
	private SportRepository sportRepo; 

	
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
	
	@Transactional
	public Activity insert(Activity obj) {
		obj.setId(null);
		return repo.save(obj); 
	}
	
	public Activity update(Activity obj) {
		Activity newObj = find(obj.getId());
		//updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir a atividade " + id);
		}		
	}
	
	public Activity fromDTO(ActivityDTO objDTO) {
		Activity act = new Activity(objDTO);

		act.setCityStart(loadCity(objDTO.getCityStartId()));
		act.setCityEnd(loadCity(objDTO.getCityEndId()));
		
		act.setSport(loadSport(objDTO.getSportId()));

		UserSS userSS = UserLoggedService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Usuário não está logado");
		}
		act.setOwner(loadUser(userSS.getId()));		
		
		act.getDays().clear();
		if(objDTO.getSchedule().isMonday()) {
			act.getDays().add(WeekDays.MONDAY);
		}
		if(objDTO.getSchedule().isTuesday()) {
			act.getDays().add(WeekDays.TUESDAY);
		}
		if(objDTO.getSchedule().isWednesday()) {
			act.getDays().add(WeekDays.WEDNESDAY);
		}
		if(objDTO.getSchedule().isThursday()) {
			act.getDays().add(WeekDays.THURSDAY);
		}
		if(objDTO.getSchedule().isFriday()) {
			act.getDays().add(WeekDays.FRIDAY);
		}
		if(objDTO.getSchedule().isSaturday()) {
			act.getDays().add(WeekDays.SATURDAY);
		}
		if(objDTO.getSchedule().isSunday()) {
			act.getDays().add(WeekDays.SUNDAY);
		}
		
		return act;
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

	private User loadUser(Integer id) {
		Optional<User> userOpt = userRepo.findById(id);
		return userOpt.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));		
	}
}

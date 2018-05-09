package br.com.qparceria.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
		updateData(newObj, obj);
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

		Set<WeekDays> days = new HashSet<>();	
		if(objDTO.getSchedule().isMonday()) {
			days.add(WeekDays.MONDAY);
		}
		if(objDTO.getSchedule().isTuesday()) {
			days.add(WeekDays.TUESDAY);
		}
		if(objDTO.getSchedule().isWednesday()) {
			days.add(WeekDays.WEDNESDAY);
		}
		if(objDTO.getSchedule().isThursday()) {
			days.add(WeekDays.THURSDAY);
		}
		if(objDTO.getSchedule().isFriday()) {
			days.add(WeekDays.FRIDAY);
		}
		if(objDTO.getSchedule().isSaturday()) {
			days.add(WeekDays.SATURDAY);
		}
		if(objDTO.getSchedule().isSunday()) {
			days.add(WeekDays.SUNDAY);
		}
		act.setDays(days);

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
	
	private void updateData(Activity newObj, Activity obj) {
		newObj.setReferencePointStart(obj.getReferencePointStart());
		newObj.setReferencePointEnd(obj.getReferencePointEnd());
		newObj.setCityStart(obj.getCityStart());
		newObj.setCityEnd(obj.getCityEnd());
		newObj.setNameRoute(obj.getNameRoute());
		newObj.setTypeRoute(obj.getTypeRoute());
		newObj.setTimeStart(obj.getTimeStart());
		newObj.setTotalTime(obj.getTotalTime());
		
		newObj.setHappenOnRain(obj.isHappenOnRain());
		newObj.setHappenOnSun(obj.isHappenOnSun());
		newObj.setHappenOnHeat(obj.isHappenOnHeat());
		newObj.setHappenOnCold(obj.isHappenOnCold());

		newObj.setForBegginers(obj.isForBegginers());
		newObj.setForRegulars(obj.isForRegulars());
		newObj.setForExperts(obj.isForExperts());
		
		newObj.setDistance(obj.getDistance());
		newObj.setAltimetry(obj.getAltimetry());
		newObj.setAverageSpeed(obj.getAverageSpeed());
		newObj.setMinPeople(obj.getMinPeople());
		
		newObj.setFrequency(obj.getFrequency());
		newObj.setDate(obj.getDate());
		newObj.setDays(obj.getDays());

		newObj.setSport(obj.getSport());
	}

}

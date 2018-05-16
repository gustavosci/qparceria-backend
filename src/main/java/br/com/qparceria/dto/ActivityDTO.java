package br.com.qparceria.dto;

import java.io.Serializable;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.domain.enuns.WeekDays;
import br.com.qparceria.services.validations.ActivitySave;

@ActivitySave
public class ActivityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String referencePointStart;
	private String referencePointEnd;
	private Integer cityStartId;
	private Integer ufStartId;
	private Integer cityEndId;
	private Integer ufEndId;
	private String typeRoute;
	private String nameRoute;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime timeStart;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime totalTime;

	private ActivityScheduleDTO schedule;
	private ActivityDetailsDTO details;

	private Integer sportId;
	private Integer ownerId;
	
	public ActivityDTO() {		
	}
	
	public ActivityDTO(Activity obj) {
		super();
		this.id = obj.getId();
		this.referencePointStart = obj.getReferencePointStart();
		this.referencePointEnd = obj.getReferencePointEnd();
		this.cityStartId = obj.getCityStart().getId();
		this.ufStartId = obj.getCityStart().getUf().getId();
		this.cityEndId = obj.getCityEnd().getId();
		this.ufEndId = obj.getCityEnd().getUf().getId();
		this.typeRoute = obj.getTypeRoute();
		this.nameRoute = obj.getNameRoute();
		this.timeStart = obj.getTimeStart();
		this.totalTime = obj.getTotalTime();
		
		this.schedule = new ActivityScheduleDTO();		
		this.schedule.setFrequency(obj.getFrequency());
		this.schedule.setDate(obj.getDate());
		for(WeekDays w : obj.getDays()) {
			if(w.equals(WeekDays.MONDAY)) {
				this.schedule.setMonday(true);
			} else if (w.equals(WeekDays.TUESDAY)) {
				this.schedule.setTuesday(true);
			} else if (w.equals(WeekDays.WEDNESDAY)) {
				this.schedule.setWednesday(true);
			} else if (w.equals(WeekDays.THURSDAY)) {
				this.schedule.setThursday(true);
			} else if (w.equals(WeekDays.FRIDAY)) {
				this.schedule.setFriday(true);
			} else if (w.equals(WeekDays.SATURDAY)) {
				this.schedule.setSaturday(true);
			} else if (w.equals(WeekDays.SUNDAY)) {
				this.schedule.setSunday(true);
			}			
		}

		this.details = new ActivityDetailsDTO();		
		this.details.setHappenOnRain(obj.isHappenOnRain());
		this.details.setHappenOnSun(obj.isHappenOnSun());
		this.details.setHappenOnHeat(obj.isHappenOnHeat());
		this.details.setHappenOnCold(obj.isHappenOnCold());
		this.details.setForBegginers(obj.isForBegginers());
		this.details.setForRegulars(obj.isForRegulars());
		this.details.setForExperts(obj.isForExperts());
		this.details.setDistance(obj.getDistance());
		this.details.setAltimetry(obj.getAltimetry());
		this.details.setAverageSpeed(obj.getAverageSpeed());
		this.details.setMinPeople(obj.getMinPeople());
		
		this.sportId = obj.getSport().getId();
		this.ownerId = obj.getOwner().getId();
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferencePointStart() {
		return referencePointStart;
	}

	public void setReferencePointStart(String referencePointStart) {
		this.referencePointStart = referencePointStart;
	}

	public String getReferencePointEnd() {
		return referencePointEnd;
	}

	public void setReferencePointEnd(String referencePointEnd) {
		this.referencePointEnd = referencePointEnd;
	}

	public Integer getCityStartId() {
		return cityStartId;
	}

	public void setCityStartId(Integer cityStartId) {
		this.cityStartId = cityStartId;
	}

	public Integer getCityEndId() {
		return cityEndId;
	}

	public void setCityEndId(Integer cityEndId) {
		this.cityEndId = cityEndId;
	}

	public String getTypeRoute() {
		return typeRoute;
	}

	public void setTypeRoute(String typeRoute) {
		this.typeRoute = typeRoute;
	}

	public String getNameRoute() {
		return nameRoute;
	}

	public void setNameRoute(String nameRoute) {
		this.nameRoute = nameRoute;
	}

	public LocalTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalTime getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(LocalTime totalTime) {
		this.totalTime = totalTime;
	}

	public ActivityScheduleDTO getSchedule() {
		return schedule;
	}

	public void setSchedule(ActivityScheduleDTO schedule) {
		this.schedule = schedule;
	}

	public ActivityDetailsDTO getDetails() {
		return details;
	}

	public void setDetails(ActivityDetailsDTO details) {
		this.details = details;
	}

	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	public Integer getUfStartId() {
		return ufStartId;
	}

	public void setUfStartId(Integer ufStartId) {
		this.ufStartId = ufStartId;
	}

	public Integer getUfEndId() {
		return ufEndId;
	}

	public void setUfEndId(Integer ufEndId) {
		this.ufEndId = ufEndId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
				
}

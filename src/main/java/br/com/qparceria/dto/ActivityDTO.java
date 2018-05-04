package br.com.qparceria.dto;

import java.io.Serializable;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.services.validations.UserSave;

@UserSave
public class ActivityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String referencePointStart;
	private String referencePointEnd;
	@NotEmpty(message="A atividade deve possuir uma cidade inicial")
	private Integer cityStartId;
	@NotEmpty(message="A atividade deve possuir uma cidade final")
	private Integer cityEndId;
	private String typeRoute;
	private String nameRoute;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime timeStart;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime totalTime;

	private ActivityScheduleDTO schedule;
	private ActivityDetailsDTO details;
	
	private Integer sportId;
	
	public ActivityDTO() {		
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
				
}

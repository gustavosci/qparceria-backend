package br.com.qparceria.dto;

import java.io.Serializable;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.services.validations.ActivitySave;

@ActivitySave
public class ActivitySimpleConsultDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nameSport;
	private String siglaUFStart;
	private String nameCityStart;
	private String siglaUFEnd;
	private String nameCityEnd;
	private String nameOwner;
	@JsonFormat(pattern="HH:mm:ss")
	private LocalTime timeStart;
	private String describeFrequency;
	
	public ActivitySimpleConsultDTO() {		
	}

	public ActivitySimpleConsultDTO(Activity obj) {
		this.id = obj.getId();
		this.setNameSport(obj.getSport().getName());
		this.siglaUFStart = obj.getCityStart().getUf().getSigla();
		this.nameCityStart = obj.getCityStart().getName();
		this.siglaUFEnd = obj.getCityEnd().getUf().getSigla();
		this.nameCityEnd = obj.getCityEnd().getName();
		this.nameOwner = obj.getOwner().getName();
		this.timeStart = obj.getTimeStart();
		this.describeFrequency = obj.getFrequency().getDescribe();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSiglaUFStart() {
		return siglaUFStart;
	}

	public void setSiglaUFStart(String siglaUFStart) {
		this.siglaUFStart = siglaUFStart;
	}

	public String getNameCityStart() {
		return nameCityStart;
	}

	public void setNameCityStart(String nameCityStart) {
		this.nameCityStart = nameCityStart;
	}

	public String getSiglaUFEnd() {
		return siglaUFEnd;
	}

	public void setSiglaUFEnd(String siglaUFEnd) {
		this.siglaUFEnd = siglaUFEnd;
	}

	public String getNameCityEnd() {
		return nameCityEnd;
	}

	public void setNameCityEnd(String nameCityEnd) {
		this.nameCityEnd = nameCityEnd;
	}

	public String getNameOwner() {
		return nameOwner;
	}

	public void setNameOwner(String nameOwner) {
		this.nameOwner = nameOwner;
	}

	public LocalTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}

	public String getDescribeFrequency() {
		return describeFrequency;
	}

	public void setDescribeFrequency(String describeFrequency) {
		this.describeFrequency = describeFrequency;
	}

	public String getNameSport() {
		return nameSport;
	}

	public void setNameSport(String nameSport) {
		this.nameSport = nameSport;
	}	
				
}

package br.com.qparceria.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.enuns.Frequency;
import br.com.qparceria.services.validations.UserSave;

@UserSave
public class ActivityScheduleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer frequency;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate date;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;
	private boolean sunday;
	
	public ActivityScheduleDTO() {		
	}

	public Integer getFrequency() {
		return frequency;
	}

	public Frequency getFrequencyEnum() {
		return Frequency.toEnum(frequency);
	}

	
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency.getId();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isMonday() {
		return monday;
	}

	public void setMonday(boolean monday) {
		this.monday = monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}

	public boolean isFriday() {
		return friday;
	}

	public void setFriday(boolean friday) {
		this.friday = friday;
	}

	public boolean isSaturday() {
		return saturday;
	}

	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}

	public boolean isSunday() {
		return sunday;
	}

	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}		
	
}

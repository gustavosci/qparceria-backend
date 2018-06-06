package br.com.qparceria.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.Mate;

public class MatchDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserMatchDTO user;
	private ActivitySimpleConsultDTO act;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate date;
	
	public MatchDTO() {		
	}

	public MatchDTO(Mate match) {
		super();
		this.user = new UserMatchDTO(match.getUser());
		this.act = new ActivitySimpleConsultDTO(match.getActivity());
		this.date = match.getDate();
	}

	public UserMatchDTO getUser() {
		return user;
	}

	public void setUser(UserMatchDTO user) {
		this.user = user;
	}

	public ActivitySimpleConsultDTO getAct() {
		return act;
	}

	public void setAct(ActivitySimpleConsultDTO act) {
		this.act = act;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}

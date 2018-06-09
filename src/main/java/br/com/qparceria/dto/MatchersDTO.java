package br.com.qparceria.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.qparceria.domain.Mate;

public class MatchersDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ActivitySimpleConsultDTO act;
	private List<UserMatchDTO> matchers;
	
	public MatchersDTO() {		
	}
	
	public MatchersDTO(Mate match) {
		super();
		this.act = new ActivitySimpleConsultDTO(match.getActivity());
		this.matchers = new ArrayList<>();
	}

	public ActivitySimpleConsultDTO getAct() {
		return act;
	}

	public void setAct(ActivitySimpleConsultDTO act) {
		this.act = act;
	}

	public List<UserMatchDTO> getMatchers() {
		return matchers;
	}

	public void setMatchers(List<UserMatchDTO> matchers) {
		this.matchers = matchers;
	}
}

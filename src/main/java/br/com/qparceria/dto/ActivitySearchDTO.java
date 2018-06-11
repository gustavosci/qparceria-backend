package br.com.qparceria.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.domain.Mate;

public class ActivitySearchDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ActivitySimpleConsultDTO act;
	private boolean isUserLoggedMatcher;
	
	public ActivitySearchDTO() {		
	}

	public ActivitySearchDTO(Activity obj, Integer idUserLogged) {
		this.act = new ActivitySimpleConsultDTO(obj);
		this.isUserLoggedMatcher = false;
		List<Mate> matches = new ArrayList<>(obj.getMatches());
		for(Mate m : matches) {
			if(m.getUser().getId() == idUserLogged) {
				this.isUserLoggedMatcher = true;
				break;
			}
		}
	}

	public ActivitySimpleConsultDTO getAct() {
		return act;
	}

	public void setAct(ActivitySimpleConsultDTO act) {
		this.act = act;
	}

	public boolean isUserLoggedMatcher() {
		return isUserLoggedMatcher;
	}

	public void setUserLoggedMatcher(boolean isUserLoggedMatcher) {
		this.isUserLoggedMatcher = isUserLoggedMatcher;
	}

	
}

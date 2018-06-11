package br.com.qparceria.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.qparceria.domain.Activity;
import br.com.qparceria.domain.Mate;

public class ActivitySearchDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ActivitySimpleConsultDTO act;
	private boolean userLoggedMatcher;
	
	public ActivitySearchDTO() {		
	}

	public ActivitySearchDTO(Activity obj, Integer idUserLogged) {
		this.act = new ActivitySimpleConsultDTO(obj);
		this.userLoggedMatcher = false;
		List<Mate> matches = new ArrayList<>(obj.getMatches());
		for(Mate m : matches) {
			if(m.getUser().getId() == idUserLogged) {
				this.userLoggedMatcher = true;
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
		return userLoggedMatcher;
	}

	public void setUserLoggedMatcher(boolean isUserLoggedMatcher) {
		this.userLoggedMatcher = isUserLoggedMatcher;
	}

	
}

package br.com.qparceria.dto;

import java.io.Serializable;

public class UserLoggedMatchDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean isMatcher;
	
	public UserLoggedMatchDTO() {		
	}

	public UserLoggedMatchDTO(boolean isMatcher) {
		super();
		this.isMatcher = isMatcher;
	}

	public boolean isMatcher() {
		return isMatcher;
	}

	public void setMatcher(boolean isMatcher) {
		this.isMatcher = isMatcher;
	}
	
}

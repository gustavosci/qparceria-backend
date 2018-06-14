package br.com.qparceria.dto;

import java.io.Serializable;

public class FeedbackEmailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public FeedbackEmailDTO() {		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

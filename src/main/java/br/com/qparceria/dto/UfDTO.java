package br.com.qparceria.dto;

import java.io.Serializable;

public class UfDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String sigla;
	
	public UfDTO() {		
	}

	public UfDTO(Integer id, String sigla) {
		super();
		this.id = id;
		this.sigla = sigla;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}		
	
}

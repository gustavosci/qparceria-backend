package br.com.qparceria.dto;

import java.io.Serializable;

import br.com.qparceria.domain.User;

public class UserMatchDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private CityDTO city;
	private UfDTO uf;
	
	public UserMatchDTO() {		
	}

	public UserMatchDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.city = new CityDTO(user.getAdress().getCity().getId(), user.getAdress().getCity().getName());
		this.uf = new UfDTO(user.getAdress().getCity().getUf().getId(), user.getAdress().getCity().getUf().getName());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CityDTO getCity() {
		return city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	public UfDTO getUf() {
		return uf;
	}

	public void setUf(UfDTO uf) {
		this.uf = uf;
	}		

}

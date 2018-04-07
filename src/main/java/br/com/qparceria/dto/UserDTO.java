package br.com.qparceria.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String username;
	private String email;
	private String password;
	private Integer gender;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date birthDate;
	
	public UserDTO() {		
	}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.username = obj.getUsername();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		//this.gender = obj.getGender();
		this.birthDate = obj.getBirthDate();		
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}

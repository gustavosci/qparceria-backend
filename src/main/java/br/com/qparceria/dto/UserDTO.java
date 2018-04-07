package br.com.qparceria.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.User;
import br.com.qparceria.domain.enuns.Gender;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String name;
	private String username;
	private String email;
	private String password;
	private Integer gender;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date birthDate;
	private String facebook;
	private String twitter;
	private String instagram;
	private String strava;
	private String pic;

	
	public UserDTO() {		
	}
	
	public UserDTO(User obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.username = obj.getUsername();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.gender = obj.getGender().getId();
		this.birthDate = obj.getBirthDate();
		this.facebook = obj.getFacebook();
		this.twitter = obj.getTwitter();
		this.instagram = obj.getInstagram();
		this.strava = obj.getStrava();
		this.pic = obj.getPic();
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

	public Gender getGender() {
		return Gender.toEnum(gender);
	}

	public void setGender(Gender gender) {
		this.gender = gender.getId();;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getStrava() {
		return strava;
	}

	public void setStrava(String strava) {
		this.strava = strava;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}

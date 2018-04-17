package br.com.qparceria.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.Sport;
import br.com.qparceria.domain.User;
import br.com.qparceria.domain.enuns.Gender;
import br.com.qparceria.services.validations.UserSave;

@UserSave
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	@NotEmpty(message="Preenchimento obrigatório")
	private String username;
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="E-mail inválido")
	private String email;
	@NotEmpty(message="Preenchimento obrigatório")
	private String password;
	private Integer gender;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date birthDate;
	private String facebook;
	private String twitter;
	private String instagram;
	private String strava;
	private String pic;

	private Integer adressId;
	private String street;
	private Integer number;
	private String complement;
	private String neighborhood;
	private Integer cep;

	private Integer cityId;
	private Integer ufId;	

	private String phone;
	private String phone2;
	private String phone3;
	
	private boolean run;
	private boolean cyclism;
	private boolean walk;
	
	public UserDTO() {		
	}
	
	public UserDTO(User obj) {
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

		this.adressId = obj.getAdress().getId();
		this.street = obj.getAdress().getStreet();
		this.number = obj.getAdress().getNumber();
		this.complement = obj.getAdress().getComplement();
		this.neighborhood = obj.getAdress().getNeighborhood();
		this.cep = obj.getAdress().getCep();
		this.cityId = obj.getAdress().getCity().getId();
		this.ufId = obj.getAdress().getCity().getUf().getId();
		
		Integer i = 1; 
		for(String p : obj.getPhones()) {
			switch(i) {
				case 1:
					this.phone = p;
					break;
				case 2:
					this.phone2 = p;
					break;
				case 3:
					this.phone2 = p;
					break;	
			}
			i += 1;
		}
		
		for(Sport s : obj.getSports()) {
			if(s.getId().equals(1)) {
				this.run = true;
			}
			if(s.getId().equals(2)) {
				this.cyclism = true;
			}
			if(s.getId().equals(3)) {
				this.walk = true;
			}
		}
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

	public Integer getAdressId() {
		return adressId;
	}

	public void setAdressId(Integer adressId) {
		this.adressId = adressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getUfId() {
		return ufId;
	}

	public void setUfId(Integer ufId) {
		this.ufId = ufId;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public boolean isCyclism() {
		return cyclism;
	}

	public void setCyclism(boolean cyclism) {
		this.cyclism = cyclism;
	}

	public boolean isWalk() {
		return walk;
	}

	public void setWalk(boolean walk) {
		this.walk = walk;
	}
	
}

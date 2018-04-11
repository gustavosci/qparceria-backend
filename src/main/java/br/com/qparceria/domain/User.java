package br.com.qparceria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.qparceria.domain.enuns.Gender;
import br.com.qparceria.dto.UserDTO;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(unique=true)
	private String username;
	@Column(unique=true)
	private String email;
	@JsonIgnore
	private String password;
	private Integer gender;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date birthDate;
	private String facebook;
	private String twitter;
	private String instagram;
	private String strava;
	private String pic;

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="adress_id")
	private Adress adress;

	@ManyToMany
	@JoinTable(name = "SPORT_USER",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "sport_id")
	)
	private List<Sport> sports = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="PHONE")
	private Set<String> phones = new HashSet<>();	
	
	public User() {		
	}

	public User(Integer id, String name, String username, String email, String password, Gender gender, Date birthDate, Adress adress) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender.getId();
		this.setBirthDate(birthDate);
		this.adress = adress;
	}

	public User(UserDTO obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.username = obj.getUsername();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		if(obj.getGender() != null) {
			this.gender = obj.getGender().getId();	
		}		
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
		this.gender = gender.getId();
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

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}
	
	public List<Sport> getSports() {
		return sports;
	}

	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}

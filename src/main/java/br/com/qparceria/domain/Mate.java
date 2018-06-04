package br.com.qparceria.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * 
 * OBS: Nome da entidade é "Mate" e não "Match", porquê a palavra "match" é reservada no MySQL. 
 * Todos pontos do sistemas referenciam Match e/ou Matches, mas o nome da Table no DB é "Mate", que também significa combinar 
 * 
 */
@Entity
public class Mate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MatePK id = new MatePK();
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate date;
	
	public Mate() {		
	}

	public Mate(Activity activity, User user, LocalDate date) {
		super();
		this.date = date;
		this.id.setActivity(activity);
		this.id.setUser(user);
	}

	public MatePK getId() {
		return id;
	}

	public void setId(MatePK id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@JsonIgnore
	public Activity getActivity() {
		return this.id.getActivity();
	}

	public User getUser() {
		return this.id.getUser();
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
		Mate other = (Mate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", date=" + date + "]";
	}	
	
}

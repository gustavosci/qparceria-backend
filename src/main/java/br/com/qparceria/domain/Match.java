package br.com.qparceria.domain;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Match {

	@EmbeddedId
	private MatchPK id = new MatchPK();
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate date;
	
	public Match() {		
	}

	public Match(Activity activity, User user, LocalDate date) {
		super();
		this.date = date;
		this.id.setActivity(activity);
		this.id.setUser(user);
	}

	public MatchPK getId() {
		return id;
	}

	public void setId(MatchPK id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
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
		Match other = (Match) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}

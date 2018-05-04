package br.com.qparceria.dto;

import java.io.Serializable;

import br.com.qparceria.services.validations.UserSave;

@UserSave
public class ActivityDetailsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean happenOnRain;
	private boolean happenOnSun;
	private boolean happenOnHeat;
	private boolean happenOnCold;
	
	private boolean forBegginers;
	private boolean forRegulars;
	private boolean forExperts;
	
	private float distance;
	private float altimetry;
	private float averageSpeed;
	private Integer minPeople;
	
	public ActivityDetailsDTO() {		
	}

	public boolean isHappenOnRain() {
		return happenOnRain;
	}

	public void setHappenOnRain(boolean happenOnRain) {
		this.happenOnRain = happenOnRain;
	}

	public boolean isHappenOnSun() {
		return happenOnSun;
	}

	public void setHappenOnSun(boolean happenOnSun) {
		this.happenOnSun = happenOnSun;
	}

	public boolean isHappenOnHeat() {
		return happenOnHeat;
	}

	public void setHappenOnHeat(boolean happenOnHeat) {
		this.happenOnHeat = happenOnHeat;
	}

	public boolean isHappenOnCold() {
		return happenOnCold;
	}

	public void setHappenOnCold(boolean happenOnCold) {
		this.happenOnCold = happenOnCold;
	}

	public boolean isForBegginers() {
		return forBegginers;
	}

	public void setForBegginers(boolean forBegginers) {
		this.forBegginers = forBegginers;
	}

	public boolean isForRegulars() {
		return forRegulars;
	}

	public void setForRegulars(boolean forRegulars) {
		this.forRegulars = forRegulars;
	}

	public boolean isForExperts() {
		return forExperts;
	}

	public void setForExperts(boolean forExperts) {
		this.forExperts = forExperts;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getAltimetry() {
		return altimetry;
	}

	public void setAltimetry(float altimetry) {
		this.altimetry = altimetry;
	}

	public float getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(float averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Integer getMinPeople() {
		return minPeople;
	}

	public void setMinPeople(Integer minPeople) {
		this.minPeople = minPeople;
	}
		
}

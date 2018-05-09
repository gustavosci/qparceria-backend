package br.com.qparceria.dto;

import java.io.Serializable;
import java.math.BigDecimal;

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
	
	private BigDecimal distance;
	private BigDecimal altimetry;
	private BigDecimal averageSpeed;
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

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public BigDecimal getAltimetry() {
		return altimetry;
	}

	public void setAltimetry(BigDecimal altimetry) {
		this.altimetry = altimetry;
	}

	public BigDecimal getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(BigDecimal averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public Integer getMinPeople() {
		return minPeople;
	}

	public void setMinPeople(Integer minPeople) {
		this.minPeople = minPeople;
	}
		
}

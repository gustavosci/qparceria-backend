package br.com.qparceria.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.qparceria.domain.enuns.Frequency;
import br.com.qparceria.domain.enuns.WeekDays;
import br.com.qparceria.dto.ActivityDTO;

@Entity
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String referencePointStart;
	private String referencePointEnd;
	@ManyToOne
	@JoinColumn(name="cityStart_id")
	private City cityStart;
	@ManyToOne
	@JoinColumn(name="cityEnd_id")
	private City cityEnd;
	private String typeRoute;
	private String nameRoute;
	@JsonFormat(pattern="HHmmss")
	private LocalTime timeStart;
	
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
	
	private Integer frequency;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate date;
	@ElementCollection
	@CollectionTable(name="DAYS_ACTIVITY")
	private Set<Integer> days = new HashSet<>();	
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="sport_id")
	private Sport sport;

	@ManyToOne
	@JoinColumn(name="owner_id")
	private User owner;	

	public Activity() {		
	}
	
	public Activity(String referencePointStart, String referencePointEnd, City cityStart, City cityEnd,
			String typeRoute, String nameRoute, LocalTime timeStart, boolean happenOnRain, boolean happenOnSun,
			boolean happenOnHeat, boolean happenOnCold, boolean forBegginers, boolean forRegulars, boolean forExperts,
			BigDecimal distance, BigDecimal altimetry, BigDecimal averageSpeed, Integer minPeople, Frequency frequency, LocalDate date,
			boolean active, Sport sport, User owner) {
		super();
		this.referencePointStart = referencePointStart;
		this.referencePointEnd = referencePointEnd;
		this.cityStart = cityStart;
		this.cityEnd = cityEnd;
		this.typeRoute = typeRoute;
		this.nameRoute = nameRoute;
		this.timeStart = timeStart;
		this.happenOnRain = happenOnRain;
		this.happenOnSun = happenOnSun;
		this.happenOnHeat = happenOnHeat;
		this.happenOnCold = happenOnCold;
		this.forBegginers = forBegginers;
		this.forRegulars = forRegulars;
		this.forExperts = forExperts;
		this.distance = distance;
		this.altimetry = altimetry;
		this.averageSpeed = averageSpeed;
		this.minPeople = minPeople;
		this.frequency = frequency.getId();
		this.date = date;
		this.active = active;
		this.sport = sport;
		this.owner = owner;
	}
	
	public Activity(ActivityDTO obj) {
		super();
		this.id = obj.getId();
		this.referencePointStart = obj.getReferencePointStart();
		this.referencePointEnd = obj.getReferencePointEnd();
		this.typeRoute = obj.getTypeRoute();
		this.nameRoute = obj.getNameRoute();
		this.timeStart = obj.getTimeStart();
		
		this.happenOnRain = obj.getDetails().isHappenOnRain();
		this.happenOnSun = obj.getDetails().isHappenOnSun();
		this.happenOnCold = obj.getDetails().isHappenOnCold();
		this.happenOnHeat = obj.getDetails().isHappenOnHeat();		
		this.forBegginers = obj.getDetails().isForBegginers();
		this.forRegulars = obj.getDetails().isForRegulars();
		this.forExperts = obj.getDetails().isForExperts();
		this.distance = obj.getDetails().getDistance();
		this.altimetry = obj.getDetails().getAltimetry();
		this.averageSpeed = obj.getDetails().getAverageSpeed();
		this.minPeople = obj.getDetails().getMinPeople();
	
		if(obj.getSchedule().getFrequency() != null) {
			this.frequency = obj.getSchedule().getFrequency().getId();	
		}
		this.date = obj.getSchedule().getDate();
		
		this.active = true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferencePointStart() {
		return referencePointStart;
	}

	public void setReferencePointStart(String referencePointStart) {
		this.referencePointStart = referencePointStart;
	}

	public String getReferencePointEnd() {
		return referencePointEnd;
	}

	public void setReferencePointEnd(String referencePointEnd) {
		this.referencePointEnd = referencePointEnd;
	}

	public City getCityStart() {
		return cityStart;
	}

	public void setCityStart(City cityStart) {
		this.cityStart = cityStart;
	}

	public City getCityEnd() {
		return cityEnd;
	}

	public void setCityEnd(City cityEnd) {
		this.cityEnd = cityEnd;
	}

	public String getTypeRoute() {
		return typeRoute;
	}

	public void setTypeRoute(String typeRoute) {
		this.typeRoute = typeRoute;
	}

	public String getNameRoute() {
		return nameRoute;
	}

	public void setNameRoute(String nameRoute) {
		this.nameRoute = nameRoute;
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

	public Frequency getFrequency() {
		return Frequency.toEnum(frequency);
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency.getId();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public LocalTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}

	public Set<WeekDays> getDays() {
		return days.stream().map(day -> WeekDays.toEnum(day)).collect(Collectors.toSet());
	}

	public void setDays(Set<WeekDays> days) {		
		this.days = days.stream().map(day -> day.getId()).collect(Collectors.toSet());
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
		Activity other = (Activity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}

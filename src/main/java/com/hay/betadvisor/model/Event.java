package com.hay.betadvisor.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hay.betadvisor.model.dto.EventDto;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date date;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "hometeam_id", referencedColumnName = "id")
	private Team homeTeam;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "guestteam_id", referencedColumnName = "id")
	private Team guestTeam;

	@OneToMany(mappedBy = "event")
	private Set<Offer> offers;

	public Event() {
		offers = new HashSet<Offer>();
	}

	public Event(EventDto e) {
		this.date = e.getDate();
		offers = new HashSet<Offer>();
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(Team guestTeam) {
		this.guestTeam = guestTeam;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	
	public void addOffer(Offer offer) {
		offers.add(offer);
	}

}

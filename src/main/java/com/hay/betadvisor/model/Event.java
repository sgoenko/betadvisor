package com.hay.betadvisor.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hay.betadvisor.model.dto.EventDto;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bookmaker_id", referencedColumnName = "id")
	private Bookmaker bookmaker;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hometeam_id", referencedColumnName = "id")
	private Team homeTeam;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guestteam_id", referencedColumnName = "id")
	private Team guestTeam;

	private Date date;
	private double home;
	private double guest;
	private double draw;
	private double profit;

	public Event() {
	}

	public Event(EventDto e) {
		this.id = e.getId();
		this.date = e.getDate();
		this.home = e.getHome();
		this.guest = e.getGuest();
		this.draw = e.getDraw();
		this.profit = e.getProfit();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bookmaker getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(Bookmaker bookmaker) {
		this.bookmaker = bookmaker;
	}

	public Date getDate() {
		return date;
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

	public void setDate(Date date) {
		this.date = date;
	}

	public double getHome() {
		return home;
	}

	public void setHome(double home) {
		this.home = home;
	}

	public double getGuest() {
		return guest;
	}

	public void setGuest(double guest) {
		this.guest = guest;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getDraw() {
		return draw;
	}

	public void setDraw(double draw) {
		this.draw = draw;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit() {
		profit = (1 / home + 1 / guest + 1 / draw - 1) * 100;
	}

}

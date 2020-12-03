package com.hay.betadvisor.model.dto;

import java.util.Date;

import com.hay.betadvisor.model.Offer;
import com.hay.betadvisor.model.utils.Bets;

public class EventDto {

	private int id;
	private Bets bookmaker;
	private Date date;
	private String homeTeam;
	private String guestTeam;
	private double home;
	private double guest;
	private double draw;
	private double profit;

	public EventDto(Offer offer) {
		this.id = offer.getId();
		this.bookmaker = offer.getBookmaker().getName();
		this.date = offer.getEvent().getDate();
		this.homeTeam = offer.getEvent().getHomeTeam().getName();
		this.guestTeam = offer.getEvent().getGuestTeam().getName();
		this.home = offer.getHome();
		this.guest = offer.getGuest();
		this.draw = offer.getDraw();
		this.profit = offer.getProfit();
	}

	public EventDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bets getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(Bets bookmaker) {
		this.bookmaker = bookmaker;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
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

	public double getDraw() {
		return draw;
	}

	public void setDraw(double draw) {
		this.draw = draw;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public void setProfit() {
		profit = (1 / home + 1 / guest + 1 / draw - 1) * 100;
	}

}

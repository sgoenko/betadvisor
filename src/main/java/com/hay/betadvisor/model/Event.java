package com.hay.betadvisor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hay.betadvisor.model.utils.BmName;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private BmName bookmaker;
	private Date date;
	private String description;
	private double home;
	private String homeurl;
	private double guest;
	private String guesturl;
	private double draw;
	private String drawurl;
	private double profit;

	@Override
	public String toString() {
		return id + ", " + bookmaker + ", " + date + ", " + description + ", " + home + ", " + draw + ", " + guest + " "
				+ profit;
	}

	public Event() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BmName getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(BmName bookmaker) {
		this.bookmaker = bookmaker;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getHome() {
		return home;
	}

	public void setHome(double home) {
		this.home = home;
	}

	public String getHomeurl() {
		return homeurl;
	}

	public void setHomeurl(String homeurl) {
		this.homeurl = homeurl;
	}

	public double getGuest() {
		return guest;
	}

	public void setGuest(double guest) {
		this.guest = guest;
	}

	public String getGuesturl() {
		return guesturl;
	}

	public void setGuesturl(String guesturl) {
		this.guesturl = guesturl;
	}

	public String getDrawurl() {
		return drawurl;
	}

	public void setDrawurl(String drawurl) {
		this.drawurl = drawurl;
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

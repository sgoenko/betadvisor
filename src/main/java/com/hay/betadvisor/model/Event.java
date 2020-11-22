package com.hay.betadvisor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private BmName bookmaker;
	private Date date;
	private String description;
	private double firstWin;
	private double secondWin;
	private double draw;
	private double profit;

	@Override
	public String toString() {
		return id + ", " + bookmaker + ", " + date + ", " + description + ", " + firstWin + ", " + draw + ", "
				+ secondWin + " " + profit;
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

	public double getFirstWin() {
		return firstWin;
	}

	public void setFirstWin(double firstWin) {
		this.firstWin = firstWin;
	}

	public double getSecondWin() {
		return secondWin;
	}

	public void setSecondWin(double secondWin) {
		this.secondWin = secondWin;
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
		profit = (1 / firstWin + 1 / secondWin + 1 / draw - 1) * 100;
	}

}

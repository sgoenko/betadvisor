package com.hay.betadvisor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hay.betadvisor.model.utils.Bets;

@Entity
@Table(name = "bookmaker")
public class Bookmaker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Bets name;

	public Bookmaker(int id, Bets name) {
		this.id = id;
		this.name = name;
	}

	public Bookmaker() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bets getName() {
		return name;
	}

	public void setName(Bets name) {
		this.name = name;
	}

}

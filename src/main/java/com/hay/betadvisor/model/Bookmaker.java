package com.hay.betadvisor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookmaker")
public class Bookmaker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private BmName name;

	public Bookmaker(int id, BmName name) {
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

	public BmName getName() {
		return name;
	}

	public void setName(BmName name) {
		this.name = name;
	}

}

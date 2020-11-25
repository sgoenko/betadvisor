package com.hay.betadvisor.model;

import com.hay.betadvisor.model.utils.BmName;

public class Bookmaker {

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

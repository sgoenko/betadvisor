package com.hay.betadvisor.model;

import java.util.ArrayList;
import java.util.List;

public class ListOfBookmakers {
	private List<Bookmaker> bookmakers = new ArrayList<>();

	public List<Bookmaker> getBookmakers() {
		return bookmakers;
	}

	public void setBookmakers(List<Bookmaker> bookmakers) {
		this.bookmakers = bookmakers;
	}

	public void add(Bookmaker bookmaker) {
		bookmakers.add(bookmaker);
	}
}

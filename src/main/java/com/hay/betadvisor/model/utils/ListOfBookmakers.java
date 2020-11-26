package com.hay.betadvisor.model.utils;

import java.util.ArrayList;
import java.util.List;

import com.hay.betadvisor.model.Bookmaker;

public class ListOfBookmakers {
	private List<Bookmaker> bookmakers = new ArrayList<>();
	private Sport selectedSport;

	public Sport getSelectedSport() {
		return selectedSport;
	}

	public void setSelectedSport(Sport selectedSport) {
		this.selectedSport = selectedSport;
	}

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

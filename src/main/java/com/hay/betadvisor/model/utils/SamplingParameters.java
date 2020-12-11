package com.hay.betadvisor.model.utils;

import java.util.ArrayList;
import java.util.List;

import com.hay.betadvisor.model.Bookmaker;

public class SamplingParameters {
	private List<Bookmaker> bookmakers = new ArrayList<>();
	private Sport selectedSport;
	private Long scrollPosition;

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

	public Long getScrollPosition() {
		return scrollPosition;
	}

	public void setScrollPosition(Long scrollPosition) {
		this.scrollPosition = scrollPosition;
	}
}

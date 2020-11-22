package com.hay.betadvisor.scrape;

public class BetEvent {
	private String team;
	private Double rate;

	public BetEvent(String team, Double rate) {
		this.team = team;
		this.rate = rate;
	}

	public BetEvent() {
	}

	public String getTeam() {
		return team;
	}

	public Double getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return team + ": " + rate;
	}

}

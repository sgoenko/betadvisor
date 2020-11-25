package com.hay.betadvisor.scrape.utils;

public class BetEvent {
	private String team;
	private Double rate;
	private String url;

	public BetEvent(String team, Double rate, String url) {
		this.team = team;
		this.rate = rate;
		this.url = url;
	}

	public BetEvent() {
	}

	public String getUrl() {
		return url;
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

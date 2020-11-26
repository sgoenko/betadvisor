package com.hay.betadvisor.scrape;

import java.util.Date;
import java.util.List;

import com.hay.betadvisor.model.utils.BmName;
import com.hay.betadvisor.model.utils.Sport;
import com.hay.betadvisor.scrape.utils.BetEvent;
import com.hay.betadvisor.model.Event;

public abstract class Scrapper {
	String url;
	BmName bmName;
	static Sport selectedSport;
	
	public abstract List<Event> scrapeAndGetEvents();
	
	void addEvent(List<Event> events, Date date, List<BetEvent> ev) {
		Event event = new Event();
		event.setDate(date);
		event.setBookmaker(bmName);
		event.setDescription(ev.get(0).getTeam() + " vs " + ev.get(2).getTeam());
		event.setHome(ev.get(0).getRate());
		event.setHomeurl(ev.get(0).getUrl());
		event.setDraw(ev.get(1).getRate());
		event.setHomeurl(ev.get(1).getUrl());
		event.setGuest(ev.get(2).getRate());
		event.setHomeurl(ev.get(2).getUrl());
		event.setProfit();

		events.add(event);
//		System.out.println(event);
	}

}

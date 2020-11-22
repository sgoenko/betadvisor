package com.hay.betadvisor.scrape;

import java.util.Date;
import java.util.List;

import com.hay.betadvisor.model.BmName;
import com.hay.betadvisor.model.Event;

public abstract class Scrapper {
	String footballUrl;
	BmName bmName;
	public abstract List<Event> scrapeAndGetEvents();
	
	void addEvent(List<Event> events, Date date, List<BetEvent> ev) {
		Event event = new Event();
		event.setDate(date);
		event.setBookmaker(bmName);
		event.setDescription(ev.get(0).getTeam() + " vs " + ev.get(2).getTeam());
		event.setFirstWin(ev.get(0).getRate());
		event.setDraw(ev.get(1).getRate());
		event.setSecondWin(ev.get(2).getRate());
		event.setProfit();

		events.add(event);
//		System.out.println(event);
	}

}

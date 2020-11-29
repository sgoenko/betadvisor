package com.hay.betadvisor.scrape;

import java.util.Date;
import java.util.List;

import com.hay.betadvisor.model.utils.Bets;
import com.hay.betadvisor.model.utils.Sport;
import com.hay.betadvisor.scrape.utils.BetEvent;
import com.hay.betadvisor.model.dto.EventDto;

public abstract class Scrapper {
	String url;
	Bets bmName;
	static Sport selectedSport;
	
	public abstract List<EventDto> scrapeAndGetEvents();
	
	void addEvent(List<EventDto> events, Date date, List<BetEvent> ev) {
		EventDto event = new EventDto();
		event.setDate(date);
		event.setBookmaker(bmName);
		event.setDescription(ev.get(0).getTeam() + " vs " + ev.get(2).getTeam());
		event.setHome(ev.get(0).getRate());
		event.setDraw(ev.get(1).getRate());
		event.setGuest(ev.get(2).getRate());
		event.setProfit();

		events.add(event);
	}

}

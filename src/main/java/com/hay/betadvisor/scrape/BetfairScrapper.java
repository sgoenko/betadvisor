package com.hay.betadvisor.scrape;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hay.betadvisor.model.dto.EventDto;
import com.hay.betadvisor.model.utils.Bets;
import com.hay.betadvisor.model.utils.Sport;
import com.hay.betadvisor.scrape.utils.BetEvent;

public class BetfairScrapper extends Scrapper {

	Map<Sport, String> urls = new HashMap<>() {
		{
			put(Sport.Football, "https://www.betfair.com/sport/football");
			put(Sport.Hockey, "https://www.betfair.com/sport/ice-hockey");
		}
	};

	public BetfairScrapper() {
		bmName = Bets.Betfair;
		url = urls.get(selectedSport);
	}

	@Override
	public List<EventDto> scrapeAndGetEvents() {
		List<EventDto> events = new ArrayList<>();

		try {
			Document doc = Jsoup.connect(url).get();

			Elements betevents = doc.select("div.event-information.ui-event");

//			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy dd MMM HH:mm", Locale.ENGLISH);
//			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

			for (Element betevent : betevents) {
				EventDto eventDto = new EventDto();
				eventDto.setBookmaker(bmName);
				
				Elements time = betevent.select("span.date");
				if (time.isEmpty())
					continue;

				String dt = time.get(0).text();
				Date date = new Date();
				eventDto.setDate(date);

				Elements teams = betevent.select("span.team-name");
				int i = 0;
				for (Element team: teams) {
					if (i==0)
						eventDto.setHomeTeam(team.text());
					else
						eventDto.setGuestTeam(team.text());
					i++;
				}

				Elements prices = betevent.select("div.market-3-runners span.ui-runner-price");
				i = 0;
				for (Element price: prices) {
					if (i==0)
						eventDto.setHome(Double.parseDouble(price.text()));
					else if (i==1)
						eventDto.setDraw(Double.parseDouble(price.text()));
					else
						eventDto.setGuest(Double.parseDouble(price.text()));
					i++;
				}
				
				eventDto.setProfit();
				events.add(eventDto);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}

}

package com.hay.betadvisor.scrape;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.utils.BmName;
import com.hay.betadvisor.scrape.utils.BetEvent;

public class WilliamHillScrapper extends Scrapper {

	public WilliamHillScrapper() {
		bmName = BmName.WilliamHill;
		footballUrl = "https://sports.williamhill.com/betting/en-gb/football";
	}

	@Override
	public List<Event> scrapeAndGetEvents() {
		List<Event> events = new ArrayList<>();
		String url = footballUrl;
		try {
			Document doc = Jsoup.connect(url).get();

			Elements betevents = doc.select("div.event");

			for (Element betevent : betevents) {
				Elements time = betevent.select("time");
				if (time.isEmpty())
					continue;

				String dt = time.get(0).attr("datetime");
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss");
				formatter.setTimeZone(TimeZone.getTimeZone("Europe/London"));
				Date date = formatter.parse(dt);

				Elements bets = betevent.select("div.btmarket__selection > button");

				List<BetEvent> ev = new ArrayList<BetEvent>();
				for (Element bet : bets) {
					String EventName = bet.attr("data-name");
					if (EventName.length() < 30) {
						Pattern p = Pattern.compile("([0-9]+)/([0-9]+)");
						Matcher m;
						double odds = 1;
						if ((m = p.matcher(bet.attr("data-odds"))).find()) {
							double a = Integer.parseInt(m.group(1));
							double b = Integer.parseInt(m.group(2));
							odds = 1.0 + a / b;
						}
						String eventUrl = "";
						ev.add(new BetEvent(EventName, odds, eventUrl));
					}
				}
				if (!ev.isEmpty()) {
					addEvent(events, date, ev);
//					System.out.println(event);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}

}

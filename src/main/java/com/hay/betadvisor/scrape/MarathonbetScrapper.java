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

public class MarathonbetScrapper extends Scrapper {

	Map<Sport, String> urls = new HashMap<>() {
		{
			put(Sport.Football, "https://www.marathonbet.com/en/popular/Football+-+11");
			put(Sport.Hockey, "https://www.marathonbet.com/en/popular/Ice+Hockey+-+537");
		}
	};

	public MarathonbetScrapper() {
		bmName = Bets.Marathon;
		url = urls.get(selectedSport);
	}

	@Override
	public List<EventDto> scrapeAndGetEvents() {
		List<EventDto> events = new ArrayList<>();

		try {
			Document doc = Jsoup.connect(url).get();

			Elements betevents = doc.select("div.bg");

			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy dd MMM HH:mm", Locale.ENGLISH);
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

			for (Element betevent : betevents) {
				Elements time = betevent.select("td.date");
				if (time.isEmpty())
					continue;

				String dt = time.get(0).text();
				Date date;
				if (dt.length() == 5) { // today
					String hour = dt.substring(0, 2);
					String min = dt.substring(3);

					calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
					calendar.set(Calendar.MINUTE, Integer.parseInt(min));
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MILLISECOND, 0);

					date = calendar.getTime();

				} else {
					dt = calendar.get(Calendar.YEAR) + " " + dt;

					date = formatter.parse(dt);
				}

				Elements bets = betevent.select("td.price");
				if (bets.size() == 3) { // only events with 3 results

					List<BetEvent> ev = new ArrayList<BetEvent>();
					for (Element bet : bets) {
						JSONObject jo = new JSONObject(bet.attr("data-sel"));
						String sn = jo.getString("sn");
						sn = sn.replace(" To Win", "");

						ev.add(new BetEvent(sn, jo.getDouble("epr")));
					}
					if (!ev.isEmpty()) {
						addEvent(events, date, ev);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return events;
	}

}

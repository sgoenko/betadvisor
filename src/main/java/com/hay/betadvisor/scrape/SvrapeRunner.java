package com.hay.betadvisor.scrape;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import com.hay.betadvisor.model.utils.Bets;
import com.hay.betadvisor.model.utils.Sport;

public class SvrapeRunner {

	public static void main(String[] args) throws MalformedURLException, IOException, Exception {

		ScrapperFactory scrapperFactory = new ScrapperFactory();
		Scrapper scrapper = scrapperFactory.getScrapper(Sport.Football, Bets.WilliamHill);
		Date begin = new Date();
		scrapper.scrapeAndGetEvents();
		Date end = new Date();
		System.out.println(end.getTime() - begin.getTime());

	}

}

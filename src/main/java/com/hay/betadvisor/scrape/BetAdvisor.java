package com.hay.betadvisor.scrape;

import java.io.IOException;
import java.net.MalformedURLException;

public class BetAdvisor {

	public static void main(String[] args) throws MalformedURLException, IOException, Exception {

		Scrapper williamHillScrapper = new WilliamHillScrapper();
		williamHillScrapper.scrapeAndGetEvents();
		Scrapper marathonbetScrapper = new MarathonbetScrapper();
		marathonbetScrapper.scrapeAndGetEvents();

	}

}

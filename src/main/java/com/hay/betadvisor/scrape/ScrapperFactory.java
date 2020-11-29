package com.hay.betadvisor.scrape;

import com.hay.betadvisor.model.utils.Bets;
import com.hay.betadvisor.model.utils.Sport;
import com.hay.betadvisor.scrape.utils.UndefinedBookmakerException;

public class ScrapperFactory {

	public Scrapper getScrapper(Sport selectedSport, Bets bmName) throws UndefinedBookmakerException {
		Scrapper.selectedSport = selectedSport;
		if (bmName == Bets.WilliamHill) {
			return new WilliamHillScrapper();
		} else if (bmName == Bets.Marathon) {
			return new MarathonbetScrapper();
		} else {
			throw new UndefinedBookmakerException("No such bookmaker");
		}
	}

}

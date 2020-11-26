package com.hay.betadvisor.scrape;

import com.hay.betadvisor.model.utils.BmName;
import com.hay.betadvisor.model.utils.Sport;
import com.hay.betadvisor.scrape.utils.UndefinedBookmakerException;

public class ScrapperFactory {

	public Scrapper getScrapper(Sport selectedSport, BmName bmName) throws UndefinedBookmakerException {
		Scrapper.selectedSport = selectedSport;
		if (bmName == BmName.WilliamHill) {
			return new WilliamHillScrapper();
		} else if (bmName == BmName.Marathon) {
			return new MarathonbetScrapper();
		} else {
			throw new UndefinedBookmakerException("No such bookmaker");
		}
	}

}

package com.hay.betadvisor.scrape;

import com.hay.betadvisor.model.BmName;

public class ScrapperFactory {

	public Scrapper getScrapper(BmName bmName) throws UndefinedBookmakerException {
		if (bmName == BmName.WilliamHill) {
			return new WilliamHillScrapper();
		} else if (bmName == BmName.Marathon) {
			return new MarathonbetScrapper();
		} else {
			throw new UndefinedBookmakerException("No such bookmaker");
		}
	}

}

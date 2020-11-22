package com.hay.betadvisor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.hay.betadvisor.model.BmName;
import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.scrape.Scrapper;
import com.hay.betadvisor.scrape.ScrapperFactory;
import com.hay.betadvisor.scrape.UndefinedBookmakerException;
import com.hay.betadvisor.service.BookmakerService;
import com.hay.betadvisor.service.EventService;

@Controller
public class BetController {
	@Autowired
	BookmakerService bookmakerService;
	@Autowired
	EventService eventService;

	@GetMapping("/")
	public String getEvents(Model model) {
		List<Event> events = eventService.findAll();
		model.addAttribute("events", events);
		return "events";
	}

	@GetMapping("/update")
	public String updateEvents(@ModelAttribute HashMap<BmName, Boolean> bmSelect, Model model) {

		if (bmSelect.isEmpty()) {
//			bmSelect = new HashMap<BmName, Boolean>();
			for (BmName bmName : BmName.values()) {
				bmSelect.put(bmName, true);
			}
		}
		model.addAttribute("bmSelect", bmSelect);

//		Bookmaker bookmaker = new Bookmaker();
//		bookmaker.setName(BmName.WilliamHill);
//		bookmakerService.addBookmaker(bookmaker);

		List<Event> events = new ArrayList<>();

		ScrapperFactory scrapperFactory = new ScrapperFactory();
		for (BmName bmName : bmSelect.keySet()) {
			if (bmSelect.get(bmName) == true) {
				try {
					Scrapper scrapper = scrapperFactory.getScrapper(bmName);
					events = scrapper.scrapeAndGetEvents();

					for (Event event : events) {
						eventService.addEvent(event);
					}
				} catch (UndefinedBookmakerException e) {
					System.out.println("Undefined bookmaker: " + bmName);
				}
			}
		}

		return "redirect:/";

	}

}

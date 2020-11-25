package com.hay.betadvisor.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hay.betadvisor.model.Bookmaker;
import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.utils.BmName;
import com.hay.betadvisor.model.utils.ListOfBookmakers;
import com.hay.betadvisor.scrape.Scrapper;
import com.hay.betadvisor.scrape.ScrapperFactory;
import com.hay.betadvisor.scrape.utils.UndefinedBookmakerException;
import com.hay.betadvisor.service.EventService;

@Controller
public class BetController {

	@Autowired
	EventService eventService;

	List<Bookmaker> allBookmakers;

	public BetController() {
		allBookmakers = new ArrayList<>();
		int id = 0;
		for (BmName bmName : BmName.values()) {
			allBookmakers.add(new Bookmaker(++id, bmName));
		}
	}

	@GetMapping("/")
	public String getEvents(Model model) {

		List<Event> events = eventService.findAllByDescription();
		model.addAttribute("events", events);
		return "events";
	}

	@PostMapping("/update")
	public String updateEvents(@ModelAttribute("bmList") ListOfBookmakers bmList, Model model,
			RedirectAttributes redirectAttributes) {

		if (bmList.getBookmakers().isEmpty()) {
			bmList.setBookmakers(allBookmakers);
		}
		model.addAttribute("bmList", bmList);
		model.addAttribute("allBookmakers", allBookmakers);

		eventService.deleteAll();

		List<Event> events = new ArrayList<>();

		ScrapperFactory scrapperFactory = new ScrapperFactory();
		for (Bookmaker bookmaker : bmList.getBookmakers()) {
			try {
				Scrapper scrapper = scrapperFactory.getScrapper(bookmaker.getName());
				events = scrapper.scrapeAndGetEvents();

				for (Event event : events) {
					eventService.addEvent(event);
				}
			} catch (UndefinedBookmakerException e) {
				System.out.println("Undefined bookmaker: " + bookmaker.getName());
			}
		}

		redirectAttributes.addFlashAttribute("bmList", bmList);
		redirectAttributes.addFlashAttribute("allBookmakers", allBookmakers);
		return "redirect:/";
	}

}

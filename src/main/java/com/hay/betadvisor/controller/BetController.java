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
import com.hay.betadvisor.model.dto.EventDto;
import com.hay.betadvisor.model.utils.Bets;
import com.hay.betadvisor.model.utils.SamplingParameters;
import com.hay.betadvisor.model.utils.Sport;
import com.hay.betadvisor.scrape.Scrapper;
import com.hay.betadvisor.scrape.ScrapperFactory;
import com.hay.betadvisor.scrape.utils.UndefinedBookmakerException;
import com.hay.betadvisor.service.BookmakerService;
import com.hay.betadvisor.service.EventService;
import com.hay.betadvisor.service.OfferService;

@Controller
public class BetController {

	@Autowired
	OfferService offerService;

	@Autowired
	EventService eventService;
	
	@Autowired
	BookmakerService bookmakerService;

	@GetMapping("/")
	public String getEvents(@ModelAttribute("samplingParameters") SamplingParameters samplingParameters,
			@ModelAttribute("allBookmakers") List<Bookmaker> allBookmakers, Model model) {

		if (samplingParameters.getBookmakers().isEmpty()) {
			samplingParameters.setBookmakers(allBookmakers);
		}
		model.addAttribute("samplingParameters", samplingParameters);

		List<EventDto> events = offerService.findAllOrderByDateTeam();
		model.addAttribute("events", events);
		return "offers";
	}

	@PostMapping(value = "/update", params = "event")
	public String updateByEvent(@ModelAttribute("samplingParameters") SamplingParameters samplingParameters,
			@ModelAttribute("allBookmakers") List<Bookmaker> allBookmakers, Model model) {
		
		if (samplingParameters.getBookmakers().isEmpty()) {
			samplingParameters.setBookmakers(allBookmakers);
		}
		model.addAttribute("samplingParameters", samplingParameters);

		List<Event> events = eventService.findAllOrderByDateTeam();
		model.addAttribute("events", events);		
		
		return "events";
	}

	@PostMapping(value = "/update", params = "sample")
	public String sampleOffers(@ModelAttribute("samplingParameters") SamplingParameters samplingParameters,
			@ModelAttribute("allBookmakers") List<Bookmaker> allBookmakers, Model model,
			RedirectAttributes redirectAttributes) {

		if (samplingParameters.getBookmakers().isEmpty()) {
			samplingParameters.setBookmakers(allBookmakers);
		}
		model.addAttribute("samplingParameters", samplingParameters);

		offerService.deleteAll();
		eventService.deleteAll();
		bookmakerService.deleteAll();
		
		List<EventDto> events = new ArrayList<>();

		Sport selectedSport = samplingParameters.getSelectedSport();
		ScrapperFactory scrapperFactory = new ScrapperFactory();
		for (Bookmaker bookmaker : samplingParameters.getBookmakers()) {
			try {
				bookmakerService.add(bookmaker);
				Scrapper scrapper = scrapperFactory.getScrapper(selectedSport, bookmaker.getName());
				events = scrapper.scrapeAndGetEvents();

				for (EventDto event : events) {
					offerService.addEvent(event);
				}

			} catch (UndefinedBookmakerException e) {
				System.out.println("Undefined bookmaker: " + bookmaker.getName());
			}
		}

		redirectAttributes.addFlashAttribute("samplingParameters", samplingParameters);
		return "redirect:/";
	}

	@ModelAttribute("allBookmakers")
	public List<Bookmaker> getAllBookmakers() {
		List<Bookmaker> allBookmakers = new ArrayList<>();
		int id = 0;
		for (Bets bmName : Bets.values()) {
			allBookmakers.add(new Bookmaker(++id, bmName));
		}
		return allBookmakers;
	}

	@ModelAttribute("allSports")
	public List<Sport> getAllSports() {
		List<Sport> allSports = new ArrayList<>();
		for (Sport sport : Sport.values()) {
			allSports.add(sport);
		}
		return allSports;
	}

}

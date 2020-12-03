package com.hay.betadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Bookmaker;
import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.Offer;
import com.hay.betadvisor.model.dto.EventDto;
import com.hay.betadvisor.repo.OfferRepo;

@Service
public class OfferService {
	@Autowired
	private OfferRepo repo;
	
	@Autowired
	private BookmakerService bookmakerService;
	
	@Autowired 
	private EventService eventService;
	
	public List<Offer> findAll() {
		return repo.findAll();
	}

	public List<EventDto> findAllOrderByDateTeam() {
		return repo.findAllOrderByDateTeam();
	}

	public void addEvent(EventDto e) {
		Offer offer = new Offer(e);
		
		Bookmaker bookmaker = bookmakerService.getByName(e.getBookmaker());
		offer.setBookmaker(bookmaker);
		
		Event event = eventService.getFromEventDto(e);
		offer.setEvent(event);
		
		repo.save(offer);		
	}
	
	public void deleteAll() {
		repo.deleteAll();
	}
}

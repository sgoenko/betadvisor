package com.hay.betadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Bookmaker;
import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.dto.EventDto;
import com.hay.betadvisor.repo.EventRepo;

@Service
public class EventService {
	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private BookmakerService bookmakerService;

	Event findByDescription(String description) {
		Event event = eventRepo.findByDescription(description);
		return event;
	}
	
	public List<Event> findAll() {
		return eventRepo.findAll();
	}

	public List<EventDto> findAllByDescription() {
		return eventRepo.findAllOrderByDescription();
	}

	public void addEvent(EventDto e) {
		Event event = new Event(e);
		Bookmaker bookmaker = bookmakerService.getByName(e.getBookmaker());
		event.setBookmaker(bookmaker);
		
		eventRepo.save(event);		
	}
	
	public void deleteAll() {
		eventRepo.deleteAll();
	}
}

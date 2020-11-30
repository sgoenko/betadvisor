package com.hay.betadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Bookmaker;
import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.Team;
import com.hay.betadvisor.model.dto.EventDto;
import com.hay.betadvisor.repo.EventRepo;

@Service
public class EventService {
	@Autowired
	private EventRepo eventRepo;
	
	@Autowired
	private BookmakerService bookmakerService;
	
	@Autowired
	private TeamService teamService;
	
	public List<Event> findAll() {
		return eventRepo.findAll();
	}

	public List<EventDto> findAllOrderByDateTeams() {
		return eventRepo.findAllOrderByDateTeams();
	}

	public void addEvent(EventDto e) {
		Event event = new Event(e);
		
		Bookmaker bookmaker = bookmakerService.getByName(e.getBookmaker());
		event.setBookmaker(bookmaker);
		
		String homeTeamName = e.getHomeTeam();
		Team homeTeam = teamService.getByName(homeTeamName);
		if (homeTeam == null) {
			homeTeam = new Team();
			homeTeam.setName(homeTeamName);
			teamService.add(homeTeam);
		}
		event.setHomeTeam(homeTeam);

		String guestTeamName = e.getGuestTeam();
		Team guestTeam = teamService.getByName(guestTeamName);
		if (guestTeam == null) {
			guestTeam = new Team();
			guestTeam.setName(guestTeamName);
			teamService.add(guestTeam);
		}
		event.setGuestTeam(guestTeam);
		
		eventRepo.save(event);		
	}
	
	public void deleteAll() {
		eventRepo.deleteAll();
	}
}

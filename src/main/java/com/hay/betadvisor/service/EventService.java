package com.hay.betadvisor.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.Team;
import com.hay.betadvisor.model.dto.EventDto;
import com.hay.betadvisor.repo.EventRepo;

@Service
public class EventService {
	@Autowired
	private EventRepo repo;

	@Autowired
	private TeamService teamService;

	public List<Event> findAll() {
		return repo.findAll();
	}

//	public List<EventDto> findAllOrderByDateTeams() {
//		return repo.findAllOrderByDateTeams();
//	}

	public Event addEvent(EventDto e, Team homeTeam, Team guestTeam) {
		Event event = new Event(e);

		event.setHomeTeam(homeTeam);
		event.setGuestTeam(guestTeam);

		repo.save(event);

		return event;
	}

	public Event getFromEventDto(EventDto e) {

		String homeTeamName = e.getHomeTeam();
		Team homeTeam = teamService.getByName(homeTeamName);

		String guestTeamName = e.getGuestTeam();
		Team guestTeam = teamService.getByName(guestTeamName);

		Date date = e.getDate();

		Event event = repo.getOneByDateAndTeams(date, homeTeam, guestTeam);
		if (event == null) {
			event = addEvent(e, homeTeam, guestTeam);
		}

		return event;
	}

	public void deleteAll() {
		repo.deleteAll();
	}

}

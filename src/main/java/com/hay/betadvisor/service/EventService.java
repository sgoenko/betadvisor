package com.hay.betadvisor.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.Offer;
import com.hay.betadvisor.model.Synonym;
import com.hay.betadvisor.model.Team;
import com.hay.betadvisor.model.dto.EventDto;
import com.hay.betadvisor.repo.EventRepo;

@Service
public class EventService {
	@Autowired
	private EventRepo repo;

	@Autowired
	private TeamService teamService;

	@Autowired
	private SynonymService synonymService;

	public List<Event> findAll() {
		return repo.findAll();
	}

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

	public List<Event> findAllOrderByDateTeam() {
		return repo.findAllOrderByDateTeam();
	}

	public void mergeEvents(Integer[] eventsToMerge) {
		Event baseEvent = repo.getOne(eventsToMerge[0]);

		Team baseHomeTeam = baseEvent.getHomeTeam();
		String baseHomeTeamName = baseHomeTeam.getName();
		
		Team baseGuestTeam = baseEvent.getGuestTeam();
		String baseGuestTeamName = baseGuestTeam.getName();

		for (int i = 1; i < eventsToMerge.length; i++) {
			Event event = repo.getOne(eventsToMerge[i]);
			
			Team homeTeam = event.getHomeTeam();
			Team guestTeam = event.getGuestTeam();

			for (Offer offer : event.getOffers()) {
				offer.setEvent(baseEvent);
				baseEvent.addOffer(offer);
			}

			event.getOffers().clear();
			repo.delete(event);

			String homeTeamName = homeTeam.getName();
			extractSynonym(baseHomeTeam, baseHomeTeamName, homeTeam, homeTeamName);
			
			String guestTeamName = guestTeam.getName();
			extractSynonym(baseGuestTeam, baseGuestTeamName, guestTeam, guestTeamName);
			
		}

		repo.save(baseEvent);

	}

	private void extractSynonym(Team baseTeam, String baseTeamName, Team team, String teamName) {
		if (teamName.compareTo(baseTeamName) != 0) {
			Synonym synonym = new Synonym();
			synonym.setName(teamName);
			synonym.setTeam(baseTeam);
			
			synonymService.add(synonym);
			teamService.delete(team);
		}
	}

}

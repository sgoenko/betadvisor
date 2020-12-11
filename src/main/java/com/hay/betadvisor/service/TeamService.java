package com.hay.betadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Team;
import com.hay.betadvisor.repo.TeamRepo;

@Service
public class TeamService {
	@Autowired
	private TeamRepo repo;
	
	@Autowired
	private SynonymService synonymService;

	public List<Team> findAll() {
		return repo.findAll();
	}

	public void add(Team team) {
		repo.save(team);
	}

	public void deleteAll() {
		repo.deleteAll();
	}

	public Team getByName(String name) {
		Team team = repo.getByName(name);
		
		if (team == null) {
			team = synonymService.getTeamByName(name);
		}
		
		if (team == null) {
			team = new Team();
			team.setName(name);
			add(team);
		}
		return team;
	}

	public void delete(Team team) {
		repo.delete(team);
	}
}

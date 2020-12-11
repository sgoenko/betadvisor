package com.hay.betadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Synonym;
import com.hay.betadvisor.model.Team;
import com.hay.betadvisor.repo.SynonymRepo;

@Service
public class SynonymService {
	@Autowired
	private SynonymRepo repo;

	public List<Synonym> findAll() {
		return repo.findAll();
	}

	public void add(Synonym synonym) {
		repo.save(synonym);
	}

	public void deleteAll() {
		repo.deleteAll();
	}

	public Team getTeamByName(String name) {
		Synonym synonym = repo.getByName(name);

		if (synonym == null) {
			return null;
		} else {
			return synonym.getTeam();
		}
	}
}

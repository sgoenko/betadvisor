package com.hay.betadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Bookmaker;
import com.hay.betadvisor.model.utils.Bets;
import com.hay.betadvisor.repo.BookmakerRepo;

@Service
public class BookmakerService {
	@Autowired
	private BookmakerRepo repo;
		
	public List<Bookmaker> findAll() {
		return repo.findAll();
	}
	
	public void add(Bookmaker bookmaker) {
		repo.save(bookmaker);		
	}
	
	public void deleteAll() {
		repo.deleteAll();
	}
	
	public Bookmaker getByName(Bets name) {
		return repo.getByName(name);
	}
}

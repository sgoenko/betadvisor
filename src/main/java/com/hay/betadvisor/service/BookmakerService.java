package com.hay.betadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hay.betadvisor.model.Bookmaker;
import com.hay.betadvisor.repo.BookmakerRepo;

@Service
public class BookmakerService {
	@Autowired
	private BookmakerRepo bookmakerRepo;

	Bookmaker findByName(String name) {
		Bookmaker bookmaker = bookmakerRepo.findByName(name);
		return bookmaker;
	}
	
	public List<Bookmaker> findAll() {
		return bookmakerRepo.findAll();
	}

	public void addBookmaker(Bookmaker bookmaker) {
		bookmakerRepo.save(bookmaker);		
	}
}

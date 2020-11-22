package com.hay.betadvisor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hay.betadvisor.model.Bookmaker;

public interface BookmakerRepo extends JpaRepository<Bookmaker, Integer> {
	Bookmaker findByName(String name);
}

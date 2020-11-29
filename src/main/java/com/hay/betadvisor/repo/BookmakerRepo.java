package com.hay.betadvisor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hay.betadvisor.model.Bookmaker;
import com.hay.betadvisor.model.utils.Bets;

public interface BookmakerRepo extends JpaRepository<Bookmaker, Integer>{

	Bookmaker getByName(Bets name);

}

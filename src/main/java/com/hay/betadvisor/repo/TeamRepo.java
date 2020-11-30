package com.hay.betadvisor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hay.betadvisor.model.Team;

public interface TeamRepo extends JpaRepository<Team, Long>{

	Team getByName(String name);

}

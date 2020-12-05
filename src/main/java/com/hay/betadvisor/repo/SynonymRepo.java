package com.hay.betadvisor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hay.betadvisor.model.Synonym;
import com.hay.betadvisor.model.Team;

public interface SynonymRepo extends JpaRepository<Synonym, Long>{

	Team getByName(String name);

}

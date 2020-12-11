package com.hay.betadvisor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hay.betadvisor.model.Synonym;

public interface SynonymRepo extends JpaRepository<Synonym, Long>{

	Synonym getByName(String name);

}

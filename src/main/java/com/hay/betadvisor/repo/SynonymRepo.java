package com.hay.betadvisor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hay.betadvisor.model.Synonym;

public interface SynonymRepo extends JpaRepository<Synonym, Long>{

	Synonym getByName(String name);
	
	@Query("from Synonym s left join s.team t "
			+ "order by t.name, s.name")	
	List<Synonym> findAllOrderByTeamName();

}

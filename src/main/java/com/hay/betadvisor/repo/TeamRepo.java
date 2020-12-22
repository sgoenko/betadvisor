package com.hay.betadvisor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hay.betadvisor.model.Team;

public interface TeamRepo extends JpaRepository<Team, Long>{

	Team getByName(String name);

	@Query( "from Team t right join t.synonyms s order by t.name")
	List<Team> findAllWithSynonyms();

}

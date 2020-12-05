package com.hay.betadvisor.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.Team;

public interface EventRepo extends JpaRepository<Event, Integer> {
	
	@Query("from Event e where e.date = :date and e.homeTeam = :homeTeam and e.guestTeam = :guestTeam")
	Event getOneByDateAndTeams(
			@Param("date") Date date, 
			@Param("homeTeam") Team homeTeam, 
			@Param("guestTeam") Team guestTeam);

	@Query("from Event order by date, homeTeam")
	List<Event> findAllOrderByDateTeam();
}

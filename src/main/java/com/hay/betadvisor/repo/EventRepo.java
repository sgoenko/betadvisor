package com.hay.betadvisor.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hay.betadvisor.model.Event;
import com.hay.betadvisor.model.Team;
import com.hay.betadvisor.model.dto.EventDto;

public interface EventRepo extends JpaRepository<Event, Integer> {
	
//	@Query("from Event order by date, description")
//	@Query("select new com.hay.betadvisor.model.dto.EventDto(e) "
//			+ "from Event e order by date, homeTeam, guestTeam")	
//	List<EventDto> findAllOrderByDateTeams();

	@Query("from Event e where e.date = :date and e.homeTeam = :homeTeam and e.guestTeam = :guestTeam")
	Event getOneByDateAndTeams(
			@Param("date") Date date, 
			@Param("homeTeam") Team homeTeam, 
			@Param("guestTeam") Team guestTeam);
}

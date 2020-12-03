package com.hay.betadvisor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hay.betadvisor.model.Offer;
import com.hay.betadvisor.model.dto.EventDto;

public interface OfferRepo extends JpaRepository<Offer, Integer> {
	
	@Query("select new com.hay.betadvisor.model.dto.EventDto(o) "
			+ "from Offer o left join o.event e left join e.homeTeam t "
			+ "order by e.date, t.name")	
	List<EventDto> findAllOrderByDateTeam();
}
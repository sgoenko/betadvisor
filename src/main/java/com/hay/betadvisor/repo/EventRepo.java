package com.hay.betadvisor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hay.betadvisor.model.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {
	Event findByDescription(String description);
	
	@Query("from Event order by description")
	List<Event> findAllOrderByDescription();
}

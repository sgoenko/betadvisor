package com.hay.betadvisor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hay.betadvisor.model.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {
	Event findByDescription(String description);
}

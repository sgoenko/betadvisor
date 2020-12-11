package com.hay.betadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hay.betadvisor.service.EventService;

@Controller
public class AnalyticController {
	
	@Autowired
	EventService eventService;
	
	@PostMapping(value = "/merge")
    public @ResponseBody
    void mergeEvents(@RequestBody Integer[] eventsToMerge) {
		
		eventService.mergeEvents(eventsToMerge);
    }
	
}

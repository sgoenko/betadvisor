package com.hay.betadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hay.betadvisor.service.EventService;

@Controller
public class AnalyticController {
	
	@Autowired
	EventService eventService;
	
//    @RequestMapping(value = "/merge", method = RequestMethod.POST)
//    public @ResponseBody
//    String merge(@RequestBody Integer[] eventsToMerge) {
//        for (Integer eventId : eventsToMerge) {
//            System.out.println("Event ID =>" + eventId);
//        }
//        
//        eventService.mergeEvents(eventsToMerge);
//        return "events";
//    }
    
    
}

package com.hay.betadvisor.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hay.betadvisor.model.Synonym;
import com.hay.betadvisor.service.SynonymService;

@Controller
public class SynonymController {

	@Autowired
	private SynonymService synonymService;
	
	@PostMapping(value = "/update", params = "synonym")
	public String updateByEvent(
			Model model) {
				
		List<Synonym> synonyms = synonymService.findAllOrderByTeamName();
		model.addAttribute("synonyms", synonyms);		
		
		return "synonyms";
	}
	
	@PostMapping(value = "/delete")
    public @ResponseBody
    void mergeEvents(@RequestBody Long id) {
		
		synonymService.deleteById(id);
    }
}

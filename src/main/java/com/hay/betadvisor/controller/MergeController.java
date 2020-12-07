package com.hay.betadvisor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MergeController {
    
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    public @ResponseBody
    void merge(@RequestBody Integer[] dataArrayToSend) {
        for (Integer data : dataArrayToSend) {
            System.out.println("Your Data =>" + data);
        }
    }
    
    
}

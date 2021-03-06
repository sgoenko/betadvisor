package com.hay.betadvisor.model.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hay.betadvisor.model.Bookmaker;

@Component
public class BookmakerConverter implements Converter<String, Bookmaker> {
		
	@Override
    public Bookmaker convert(String id) {

        int parsedId = Integer.parseInt(id);
        
        int index = parsedId - 1;
        List<Bookmaker> allBookmakers = new ArrayList<>();
		int i = 0;
		for (Bets bmName : Bets.values()) {
			allBookmakers.add(new Bookmaker(++i, bmName));
		}
		
        return allBookmakers.get(index);
    }
}
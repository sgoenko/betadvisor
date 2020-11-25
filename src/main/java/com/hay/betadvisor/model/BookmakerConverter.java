package com.hay.betadvisor.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookmakerConverter implements Converter<String, Bookmaker> {
		
	@Override
    public Bookmaker convert(String id) {
        System.out.println("Trying to convert id=" + id + " into a bookmaker");

        int parsedId = Integer.parseInt(id);
        
        int index = parsedId - 1;
        List<Bookmaker> allBookmakers = new ArrayList<>();
		int i = 0;
		for (BmName bmName : BmName.values()) {
			allBookmakers.add(new Bookmaker(++i, bmName));
		}
		
        return allBookmakers.get(index);
    }
}
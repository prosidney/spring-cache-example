package com.springcacheexample.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.springcacheexample.model.InfoResponse;
import com.springcacheexample.model.Thing;
import com.springcacheexample.model.ThingType;

public class AnotherService {
	
	private static InfoResponse infoResponse = new InfoResponse();
	
	@Cacheable(value = { "test" })
	public InfoResponse getSomething(ThingType thingType){
		System.out.println("----> getting " + thingType + " <---- ");
		
		if(infoResponse.getThings() == null){
			infoResponse.setThings(new ArrayList<Thing>());
		}
		
		InfoResponse infoResponse = new InfoResponse();
		infoResponse.setThings(new ArrayList<Thing>());
		
		Collection<Thing> things = this.infoResponse.getThings();
		
		for (Thing thing : things) {
			if(thing.getType() == thingType){
				infoResponse.getThings().add(thing);
			}
		}
		
		return infoResponse;
	}
	

	//@CacheEvict(value="test", key="#rating.podcastId")    
	@CacheEvict(value="test", key="#thingType")    
	public InfoResponse changeSomething(String content, ThingType thingType){
		if(infoResponse.getThings() == null){
			infoResponse.setThings(new ArrayList<Thing>());
		}
		
		Thing e = new Thing();
		e.setContent(content);
		e.setType(thingType);
		
		infoResponse.getThings().add(e);
		
		System.out.println(e.getType() + " changed");
		return infoResponse;
	}
	
	@CacheEvict(value="test", allEntries = true)    
	public InfoResponse changeALotOfThing(String contextId, String content, ThingType thingType){
		System.out.println("changeALotOfThing>>");
		
		return infoResponse;
	}
}

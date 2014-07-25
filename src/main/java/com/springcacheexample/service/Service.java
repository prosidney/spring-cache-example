package com.springcacheexample.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.springcacheexample.model.InfoResponse;
import com.springcacheexample.model.Thing;
import com.springcacheexample.model.ThingType;

public class Service {
	
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
	@CacheEvict(value="test", key="#thing.type")    
	public InfoResponse changeSomething(String content, Thing thing){
		if(infoResponse.getThings() == null){
			infoResponse.setThings(new ArrayList<Thing>());
		}
		
		Thing e = new Thing();
		e.setContent(content);
		e.setType(thing.getType());
		
		infoResponse.getThings().add(e);
		
		System.out.println(e.getType() + " changed");
		return infoResponse;
	}
	
	@Caching(evict = {
/*			@CacheEvict(value="referenceData", allEntries=true),
			@CacheEvict(value="podcasts", allEntries=true),
			@CacheEvict(value="searchResults", allEntries=true),
			@CacheEvict(value="newestAndRecommendedPodcasts", allEntries=true),*/
			@CacheEvict(value="test", allEntries=true)		    
		})	
	public void flushAllCaches() {
		System.out.println("All caches have been completely flushed");		
	}
}

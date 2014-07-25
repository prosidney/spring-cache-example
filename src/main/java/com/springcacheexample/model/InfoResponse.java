package com.springcacheexample.model;

import java.util.Collection;

public class InfoResponse {

	private Thing thing;
	private Collection<Thing> things;
	
	public InfoResponse() {
	}
	
	public Thing getThing() {
		return thing;
	}
	public void setThing(Thing thing) {
		this.thing = thing;
	}
	public Collection<Thing> getThings() {
		return things;
	}
	public void setThings(Collection<Thing> things) {
		this.things = things;
	}
	
	
}

package com.springcacheexample.controller;

import java.util.Collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcacheexample.config.EnableCachingConfig;
import com.springcacheexample.model.InfoResponse;
import com.springcacheexample.model.Thing;
import com.springcacheexample.model.ThingType;
import com.springcacheexample.service.AnotherService;

public class SomeController {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		 
		ctx.register(EnableCachingConfig.class);
		ctx.refresh();

		AnotherService anotherService = ctx.getBean(AnotherService.class);
		
		getMessage(anotherService);

		getMessage(anotherService);

		getComments(anotherService);
		
		getMessage(anotherService);
		
		getComments(anotherService);
		
		changeSomething(anotherService, ThingType.COMMENT, "Message");
		
		getComments(anotherService);
		
		getMessage(anotherService);
		
		getComments(anotherService);
		
		getMessage(anotherService);
		
		changeAlotOfThing(anotherService, ThingType.COMMENT);
		
		getComments(anotherService);
		
		getMessage(anotherService);	
		
		getComments(anotherService);
		
		getMessage(anotherService);	
	}

	private static void changeSomething(AnotherService anotherService, ThingType thingType, String content) {
		System.out.println("change " + thingType);
		InfoResponse infoResponse = anotherService.changeSomething(content, thingType);
		
		printResult(infoResponse);
		
		line();
	}

	private static void changeAlotOfThing(AnotherService anotherService, ThingType thingType) {
		System.out.println("change " + ThingType.MESSAGE);
		anotherService.changeALotOfThing("WC13712356697787927", "Message", thingType);
		
		line();
	}

	private static void getComments(AnotherService someService) {
		System.out.println("get " + ThingType.COMMENT);
		InfoResponse infoResponse = someService.getSomething(ThingType.COMMENT);
		
		printResult(infoResponse);
		
		line();
	}

	private static void getMessage(AnotherService someService) {
		System.out.println("get " + ThingType.MESSAGE);
		InfoResponse infoResponse = someService.getSomething(ThingType.MESSAGE);

		printResult(infoResponse);
		
		line();
	}
	
	private static void printResult(InfoResponse infoResponse) {
		Collection<Thing> things = infoResponse.getThings();
		
		System.out.println(things.size());
		for (Thing thing : things) {
			System.out.println(thing.getContent());
		}
	}	

	private static void line() {
		System.out.println("-----------------------------------------------------------------");
	}
}

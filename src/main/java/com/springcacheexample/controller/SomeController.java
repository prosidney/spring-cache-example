package com.springcacheexample.controller;

import java.util.Collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springcacheexample.config.EnableCachingConfig;
import com.springcacheexample.model.InfoResponse;
import com.springcacheexample.model.Thing;
import com.springcacheexample.model.ThingType;
import com.springcacheexample.service.Service;

public class SomeController {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		 
		ctx.register(EnableCachingConfig.class);
		ctx.refresh();

		Service anotherService = ctx.getBean(Service.class);
		
		getMessage(anotherService);

		getMessage(anotherService);

		getComments(anotherService);
		
		Thing thing = new Thing();
		thing.setType(ThingType.MESSAGE);
		changeSomething(anotherService, thing, "new message");
		
		getMessage(anotherService);
		
		getComments(anotherService);

		Thing thing1 = new Thing();
		thing1.setType(ThingType.COMMENT);
		changeSomething(anotherService, thing1, "new comment");
		
		getComments(anotherService);
		
		getMessage(anotherService);
		
		getComments(anotherService);
		
		getMessage(anotherService);
		
		flush(anotherService);
		
		getComments(anotherService);
		
		getMessage(anotherService);	
		
		getComments(anotherService);
		
		getMessage(anotherService);	
	}

	private static void changeSomething(Service anotherService, Thing thing, String content) {
		System.out.println("change " + thing.getType());
		InfoResponse infoResponse = anotherService.changeSomething(content, thing);
		
		printResult(infoResponse);
		
		line();
	}

	private static void flush(Service anotherService) {
		System.out.println("change " + ThingType.MESSAGE);
		anotherService.flushAllCaches();
		
		line();
	}

	private static void getComments(Service someService) {
		System.out.println("get " + ThingType.COMMENT);
		InfoResponse infoResponse = someService.getSomething(ThingType.COMMENT);
		
		printResult(infoResponse);
		
		line();
	}

	private static void getMessage(Service someService) {
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

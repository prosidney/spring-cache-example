package com.springcacheexample.model;


public enum ThingType {
	
	PERSON("person"),
    EVENT("event"),
    PLACE("place"),
    THING("thing"),
    TOPIC("topic"),
    CONTENT("content"),
    GROUP("group"),
    BADGE("badge"),
    APPLICATION("application"),
    CLIENT("client"),
    POLICY("policy"),
    SKILL("skill"),
    USER("user"),

    //do we need this??
    COLLECTION("collection"),
    CONTEXT("context"),
    COMMENT("comment"),
    MESSAGE("message"),
    REPLY("reply"),
    ;

    private String name;

    private ThingType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ThingType fromName(String name) {
        ThingType result = null;
        if (name != null) {
            for (ThingType thingType : values()) {
                if (name.equals(thingType.name)) {
                    result = thingType;
                    break;
                }
            }
        }
        return result;
    }
}
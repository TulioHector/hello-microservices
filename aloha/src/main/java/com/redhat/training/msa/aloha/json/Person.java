package com.redhat.training.msa.aloha.json;

import javax.json.JsonObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Person {
	
	protected JsonObject underlying;
	
	public Person(final JsonObject underlying) {
		this.underlying = underlying;
	}
	
	@JsonIgnore
    JsonObject getUnderlying() {
        return underlying;
    }

	public String getFirstName() {
		return underlying.getString("firstName");
	}

	public String getLastName() {
		return underlying.getString("lastName");
	}

	public String getLocation() {
		return underlying.getString("location");
	}
	
 

}

package com.redhat.training.msa.aloha.json;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

public class PersonParser {
	
	
	public static Person parse(final String json) {
		
		InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
		//TODO Create a new JsonReaderFactory with a default configuration
		JsonReaderFactory factory = Json.createReaderFactory(null);
		//TODO Use the factory to create a JsonReader for the stream 
		JsonReader reader = factory.createReader(stream);
		//TODO use the reader to read the JSON into a new JsonObject
		JsonObject object = reader.readObject();
		return new Person(object);
	}

}

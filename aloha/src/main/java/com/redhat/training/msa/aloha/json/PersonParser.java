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
		JsonReaderFactory factory = Json.createReaderFactory(null);
		JsonReader reader = factory.createReader(stream);
		JsonObject object = reader.readObject();
		return new Person(object);
	}

}

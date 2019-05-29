package com.bojan.homework.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.bojan.homework.model.Totality;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

public class JsonBuilder {

	public static JsonBuilder jsonBuilder = null;
	private File supplyFile = new File("supplies-scan.json");
	private File fixturesFile = new File("fixtures-scan.json");
	private File deviceRelationsFile = new File("devicerelations.json");

	private JsonBuilder() {
		try {
			if (!supplyFile.exists()) {
				supplyFile.createNewFile();
			}
			if (!fixturesFile.exists()) {
				fixturesFile.createNewFile();
			}
			if (deviceRelationsFile.exists()) {
				deviceRelationsFile.createNewFile();
			}
		} catch (IOException e) {
		}

	}

	public static JsonBuilder getInstance() {
		if (jsonBuilder == null) {
			jsonBuilder = new JsonBuilder();
		}
		return jsonBuilder;
	}

	private void writeJsonString(Totality totality) {
		Gson gson = new Gson();

		String supplyJson = gson.toJson(totality.getSupply());
		String fixturesJson = gson.toJson(totality.getFixtures());
		String deviceRelationsJson = gson.toJson(totality.getDeviceRelations());

		writeJsonToFile(formatJson(supplyJson), supplyFile);
		writeJsonToFile(formatJson(fixturesJson), fixturesFile);
		writeJsonToFile(formatJson(deviceRelationsJson), deviceRelationsFile);

	}

	private void writeJsonToFile(String json, File file) {
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(file/*
									 * , true
									 */))) {
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setTotality(Totality... totalities) {
		if (totalities.length == 0) {
			System.out.println("No totalities provided");
		}
		for (Totality totality : totalities) {
			writeJsonString(totality);
		}

	}

	public String formatJson(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		JsonNode tree;
		String formattedJson = null;

		try {
			tree = objectMapper.readTree(json);
			formattedJson = objectMapper.writeValueAsString(tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return formattedJson;
	}
}

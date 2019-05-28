package com.bojan.homework.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.bojan.homework.model.Totality;
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

	public void writeJsonString(Totality totality) {
		// TODO assign each object to gson toJson
		Gson gson = new Gson();

		String supplyJson = gson.toJson(totality.getSupply());
		String fixturesJson = gson.toJson(totality.getFixtures());
		String deviceRelationsJson = gson.toJson(totality.getDeviceRelations());

		writeJsonToFile(supplyJson, supplyFile);
		writeJsonToFile(fixturesJson, fixturesFile);
		writeJsonToFile(deviceRelationsJson, deviceRelationsFile);

	}

	private void writeJsonToFile(String json, File file) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

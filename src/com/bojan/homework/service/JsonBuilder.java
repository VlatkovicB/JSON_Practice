package com.bojan.homework.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.bojan.homework.model.Totality;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

public class JsonBuilder {

	public static JsonBuilder jsonBuilder = null;
	private File supplyFile = new File("files/supplies-scan.json");
	private File fixturesFile = new File("files/fixtures-scan.json");
	private File deviceRelationsFile = new File("files/devicerelations.json");

	private JsonBuilder() {
	}

	public static JsonBuilder getInstance() {
		if (jsonBuilder == null) {
			jsonBuilder = new JsonBuilder();
		}
		return jsonBuilder;
	}

	private void writeJsonString(Totality totality) throws IOException {
		Gson gson = new Gson();

		String supplyJson = gson.toJson(totality.getSupply());
		String fixturesJson = gson.toJson(totality.getFixtures());
		String deviceRelationsJson = gson.toJson(totality.getDeviceRelations());

		writeJsonToFile(formatJson(supplyJson), supplyFile);
		writeJsonToFile(formatJson(fixturesJson), fixturesFile);
		writeJsonToFile(formatJson(deviceRelationsJson), deviceRelationsFile);

	}

	private void writeJsonToFile(String json, File file) throws IOException {

		BufferedWriter writer = null;
		if (!file.exists()) {
			file.createNewFile();

			writer = new BufferedWriter(new FileWriter(file));

			System.out.println("in doesnt exist" + json);
			writer.write(json);
			writer.flush();

		} else {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder fileData = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				fileData.append(line);
			}
			fileData.substring(0, fileData.length() - 2);
			fileData.append(json).append("]");

			writer = new BufferedWriter(new FileWriter(file));

			writer.write(fileData.toString());
			writer.flush();

			reader.close();

		}
		writer.close();
	}

	public void setTotality(Totality... totalities) {
		if (totalities.length == 0) {
			System.out.println("No totalities provided");
		}

		try {
			for (Totality totality : totalities) {
				writeJsonString(totality);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String formatJson(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		JsonNode tree;
		String formattedJson = null;

		try {
			tree = objectMapper.readTree(json);
			formattedJson = objectMapper.writeValueAsString(tree);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return formattedJson;
	}
}

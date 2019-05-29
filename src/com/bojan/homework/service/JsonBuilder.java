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

	/**
	 * Creates a JSON from a Totality and submits to be written.
	 * 
	 * @param totality
	 * @throws IOException
	 */
	private void writeJsonString(Totality totality) throws IOException {
		Gson gson = new Gson();

		String supplyJson = gson.toJson(totality.getSupply());
		String fixturesJson = gson.toJson(totality.getFixtures());
		String deviceRelationsJson = gson.toJson(totality.getDeviceRelations());

		writeJsonToFile(supplyJson, supplyFile);
		writeJsonToFile(fixturesJson, fixturesFile);
		writeJsonToFile(deviceRelationsJson, deviceRelationsFile);

	}

	/**
	 * 
	 * Writes JSON to a file.
	 * 
	 * @param json
	 *            which to be appended.
	 * @param file
	 *            to be appended to.
	 * @throws IOException
	 */
	private void writeJsonToFile(String json, File file) throws IOException {

		BufferedWriter writer = null;

		/**
		 * Checks if file exists. If not makes it and writes the JSON to it.
		 */
		if (!file.exists()) {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(formatJson(json));
			writer.flush();
		} else {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder fileData = new StringBuilder();
			String line = null;

			/**
			 * Reads the file.
			 */
			while ((line = reader.readLine()) != null) {
				fileData.append(line);
			}

			/**
			 * Checks if file is empty (just in case) If read file starts with
			 * '[' (Meaning it's already JSON with an Array), removes first and
			 * last character ('[' , ']')
			 * 
			 */
			if (fileData.length() > 0 && fileData.charAt(0) == '[') {
				fileData.deleteCharAt(0).deleteCharAt(fileData.length() - 1);
			}
			/**
			 * Same as above just for the given JSON.
			 */
			if (json.charAt(0) == '[') {
				json = json.substring(1, json.length() - 1);
			}

			/**
			 * Creates a string to write in format of JSON.
			 */
			fileData.insert(0, "[");
			fileData.append(',').append("\n").append(json).append("]");

			writer = new BufferedWriter(new FileWriter(file));
			String string = fileData.toString();
			writer.write(formatJson(string));
			writer.flush();

			reader.close();

		}
		writer.close();
	}

	/**
	 * Main function, expects variable lenght array of Totalities.
	 * 
	 * @param totalities
	 */
	public void setTotality(Totality... totalities) {
		/**
		 * if length is 0, do nothing
		 */
		if (totalities.length == 0) {
			System.out.println("No totalities provided");
		} else {
			try {
				/**
				 * Submit each Totality to be converted to a JSON.
				 */
				for (Totality totality : totalities) {
					writeJsonString(totality);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Indents JSON properly
	 * 
	 * @param json
	 * @return
	 */
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

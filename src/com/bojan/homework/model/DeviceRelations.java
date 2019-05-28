package com.bojan.homework.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceRelations {
	private List<String> fixtures;
	private String ipAddress;
	private String name;

	public List<String> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {

		List<String> fixturesSerials = new ArrayList<String>();

		for (Fixture fixture : fixtures) {
			fixturesSerials.add(fixture.getSerial());
		}

		this.fixtures = fixturesSerials;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(Supply supply) {
		this.ipAddress = supply.getAddress();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeviceRelations(List<Fixture> fixtures, Supply supply,
			String deviceRelationsName) {
		List<String> fixturesSerials = new ArrayList<String>();

		for (Fixture fixture : fixtures) {
			fixturesSerials.add(fixture.getSerial());
		}
		this.fixtures = fixturesSerials;

		this.ipAddress = supply.getAddress();

		this.name = deviceRelationsName;
	}
}

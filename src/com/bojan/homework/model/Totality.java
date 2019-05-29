package com.bojan.homework.model;

import java.util.List;

public class Totality {
	private Supply supply;
	private List<Fixture> fixtures;
	private DeviceRelations deviceRelations;

	public Totality(Supply supply, List<Fixture> fixtures,
			String deviceRelationsName) {
		this.supply = supply;
		this.fixtures = fixtures;
		this.deviceRelations = new DeviceRelations(fixtures, supply,
				deviceRelationsName);
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	public DeviceRelations getDeviceRelations() {
		return deviceRelations;
	}

	public void setDeviceRelations(DeviceRelations deviceRelations) {
		this.deviceRelations = deviceRelations;
	}

	@Override
	public String toString() {
		return "Totality [supply=" + supply + ", fixtures=" + fixtures + "]";
	}

}

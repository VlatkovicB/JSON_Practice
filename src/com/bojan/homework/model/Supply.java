package com.bojan.homework.model;

import java.util.List;

public class Supply {
	private int status;
	private String name;
	private List<Property> properties;
	private String address;
	private String serial;
	private String statusDescription;

	public Supply() {

	}

	public Supply(int status, String name, List<Property> properties,
			String address, String serial, String statusDescription) {
		this.status = status;
		this.name = name;
		this.properties = properties;
		this.address = address;
		this.serial = serial;
		this.statusDescription = statusDescription;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public String toString() {
		return "Supply [status=" + status + ", name=" + name + ", serial="
				+ serial + "]";
	}

}

package com.brewerydb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleBeerSearchResult {

	private String status;
	private Beer data;

	public SingleBeerSearchResult() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Beer getData() {
		return data;
	}

	public void setData(Beer data) {
		this.data = data;
	}
}

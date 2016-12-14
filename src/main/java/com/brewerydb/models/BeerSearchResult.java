package com.brewerydb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerSearchResult {
	private int currentPage = 1;
	private int numberOfPages = 1;
	private int totalResults = 0;
	private ArrayList<Beer> data;

	public BeerSearchResult() {
		super();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public void setData(ArrayList<Beer> data) {
		this.data = data;
	}

	public ArrayList<Beer> getData() {
		return data;
	}

}

/*
 * (C) Copyright 2013 Mads Kal�r
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.brewerydb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yummly.models.Attribution;
import com.yummly.models.Criteria;
import com.yummly.models.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A recipe search result containing matches, counts, etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {

	private int currentPage;
	private int numberOfPages;
	private int totalResults;
	private ArrayList<Beer> data;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
        System.out.println("currentPage: " + currentPage);
		this.currentPage = currentPage;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
        System.out.println("numberOfPages: " + numberOfPages);
		this.numberOfPages = numberOfPages;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
        System.out.println("totalResults: " + totalResults);
		this.totalResults = totalResults;
	}

	public void setData(ArrayList<Beer> data) {
		this.data = data;
	}

	public ArrayList<Beer> getData() {
		return data;
	}

}

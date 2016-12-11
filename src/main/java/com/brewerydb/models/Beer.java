package com.brewerydb.models;

import java.util.Date;
import java.util.List;

public class Beer {
    private String id;
    private String name;
    private String nameDisplay;
    private String description;
    private Double abv;
    private Boolean isOrganic;
    private String foodPairings;
    private String statusDisplay;
    private Date createDate;
    private Date updateDate;
    private List<Brewery> breweries;

    public Beer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public Double getAbv() {
        return abv;
    }

    public Boolean getIsOrganic() {
        return isOrganic;
    }

    public String getFoodPairings() {
        return foodPairings;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public List<Brewery> getBreweries() {
        return breweries;
    }

}

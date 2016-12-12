package com.brewerydb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String status;
    private Beer data;

    public Beer() {
        super();
    }

    public Beer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    @JsonProperty("nameDisplay")
    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public Boolean getOrganic() {
        return isOrganic;
    }

    public void setOrganic(Boolean organic) {
        isOrganic = organic;
    }

    public String getFoodPairings() {
        return foodPairings;
    }

    public void setFoodPairings(String foodPairings) {
        this.foodPairings = foodPairings;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

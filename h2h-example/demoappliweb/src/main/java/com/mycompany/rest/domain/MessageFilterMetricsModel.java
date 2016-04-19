package com.highway2urhell.rest.domain;

public class MessageFilterMetricsModel {

    private String token;
    private Integer responseTime;
    private Integer nbItems;
    private String allItems;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getNbItems() {
        return nbItems;
    }

    public void setNbItems(Integer nbItems) {
        this.nbItems = nbItems;
    }
}

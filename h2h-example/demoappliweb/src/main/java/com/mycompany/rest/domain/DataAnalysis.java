package com.highway2urhell.rest.domain;


import com.highway2urhell.domain.ThunderStat;

import java.util.List;

public class DataAnalysis {

    private List<ThunderStat> listTs;
    private String token;

    public List<ThunderStat> getListTs() {
        return listTs;
    }

    public void setListTs(List<ThunderStat> listTs) {
        this.listTs = listTs;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

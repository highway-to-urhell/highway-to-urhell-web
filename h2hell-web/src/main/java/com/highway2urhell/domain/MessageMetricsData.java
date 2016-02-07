package com.highway2urhell.domain;

import com.highway2urhell.domain.MetricsTimer;

import java.util.ArrayList;
import java.util.List;

public class MessageMetricsData {

    private Integer lastInc;
    private List<MetricsTimer> listMetrics;

    public MessageMetricsData(){
        listMetrics = new ArrayList<MetricsTimer>();
    }

    public Integer getLastInc() {
        return lastInc;
    }

    public void setLastInc(Integer lastInc) {
        this.lastInc = lastInc;
    }

    public List<MetricsTimer> getListMetrics() {
        return listMetrics;
    }

    public void setListMetrics(List<MetricsTimer> listMetrics) {
        this.listMetrics = listMetrics;
    }
}

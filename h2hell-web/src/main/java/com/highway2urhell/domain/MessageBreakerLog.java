package com.highway2urhell.domain;


import com.highway2urhell.domain.LeechParamMethodData;

import java.util.List;

public class MessageBreakerLog {

    private String pathClassMethodName;
    private String token;
    private String dateIncoming;
    private List<LeechParamMethodData> parameters;

    public String getPathClassMethodName() {
        return pathClassMethodName;
    }

    public void setPathClassMethodName(String pathClassMethodName) {
        this.pathClassMethodName = pathClassMethodName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDateIncoming() {
        return dateIncoming;
    }

    public void setDateIncoming(String dateIncoming) {
        this.dateIncoming = dateIncoming;
    }

    public List<LeechParamMethodData> getParameters() {
        return parameters;
    }

    public void setParameters(List<LeechParamMethodData> parameters) {
        this.parameters = parameters;
    }
}

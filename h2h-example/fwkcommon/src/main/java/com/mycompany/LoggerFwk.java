package com.mycompany;

import ch.qos.logback.classic.Logger;

public class LoggerFwk {

    private String name;

    private String level;

    public LoggerFwk(Logger logger) {
        this.name = logger.getName();
        this.level = logger.getEffectiveLevel().toString();
    }

    public LoggerFwk() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "LoggerDTO{" +
            "name='" + name + '\'' +
            ", level='" + level + '\'' +
            '}';
    }
}

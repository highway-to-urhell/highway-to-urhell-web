package com.highway2urhell.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Event implements IdentifiableEntity<String> {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String token;
    private String reference;
    private TypeMessageEvent typeMessageEvent;
    @Lob
    private String data;
    private Boolean treat;

    public Boolean getTreat() {
        return treat;
    }

    public void setTreat(Boolean treat) {
        this.treat = treat;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public TypeMessageEvent getTypeMessageEvent() {
        return typeMessageEvent;
    }

    public void setTypeMessageEvent(TypeMessageEvent typeMessageEvent) {
        this.typeMessageEvent = typeMessageEvent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

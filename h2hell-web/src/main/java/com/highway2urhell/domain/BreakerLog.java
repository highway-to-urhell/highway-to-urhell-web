package com.highway2urhell.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "breaker")
public class BreakerLog  {
	@Id
	private String id;
	@NotNull
	private String pathClassMethodName;
	@NotNull
	private String token;
	private String dateIncoming;
	private String parameters;

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

}

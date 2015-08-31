package com.highway2urhell.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class MetricsTimer implements IdentifiableEntity<String>{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@NotNull
	private String pathClassMethodName;
	@NotNull
	private String token;
	private Date dateIncoming;
	private Long timeExec;
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
	public Date getDateIncoming() {
		return dateIncoming;
	}
	public void setDateIncoming(Date dateIncoming) {
		this.dateIncoming = dateIncoming;
	}
	public Long getTimeExec() {
		return timeExec;
	}
	public void setTimeExec(Long timeExec) {
		this.timeExec = timeExec;
	}
	
	
}

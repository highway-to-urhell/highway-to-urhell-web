package com.highway2urhell.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class MetricsTimer implements IdentifiableEntity<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@NotNull
	private String pathClassMethodName;
	@NotNull
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date dateIncoming;

	private Long timeExec;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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

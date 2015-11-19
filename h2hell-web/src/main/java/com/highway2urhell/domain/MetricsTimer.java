package com.highway2urhell.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
	private Double cpuLoadSystem;
	private Double cpuLoadProcess;
	@Lob
	private String parameters;

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Double getCpuLoadSystem() {
		return cpuLoadSystem;
	}

	public void setCpuLoadSystem(Double cpuLoadSystem) {
		this.cpuLoadSystem = cpuLoadSystem;
	}

	public Double getCpuLoadProcess() {
		return cpuLoadProcess;
	}

	public void setCpuLoadProcess(Double cpuLoadProcess) {
		this.cpuLoadProcess = cpuLoadProcess;
	}

	private Integer timeExec;
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
	public Integer getTimeExec() {
		return timeExec;
	}
	public void setTimeExec(Integer timeExec) {
		this.timeExec = timeExec;
	}




}

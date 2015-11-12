package com.highway2urhell.domain;

import javax.validation.constraints.NotNull;


public class ThunderStat implements Comparable<ThunderStat>{
	
	private String id;
	@NotNull
	private Long count;
	@NotNull
	private ThunderApp thunderApp;
	@NotNull
	private String pathClassMethodName;
	@NotNull
	private Boolean falsePositive = false;
	private String uri;
	private String httpmethod;
	private Boolean audit;
	private Long averageTime;

	public Boolean getAudit() {
		return audit;
	}

	public void setAudit(Boolean audit) {
		this.audit = audit;
	}
	
	public Long getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(Long averageTime) {
		this.averageTime = averageTime;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHttpmethod() {
		return httpmethod;
	}

	public void setHttpmethod(String httpmethod) {
		this.httpmethod = httpmethod;
	}

	public Boolean getFalsePositive() {
		return falsePositive;
	}

	public void setFalsePositive(Boolean falsePositive) {
		this.falsePositive = falsePositive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	
	public ThunderApp getThunderApp() {
		return thunderApp;
	}

	public void setThunderApp(ThunderApp thunderApp) {
		this.thunderApp = thunderApp;
	}

	public String getPathClassMethodName() {
		return pathClassMethodName;
	}

	public void setPathClassMethodName(String pathClassMethodName) {
		this.pathClassMethodName = pathClassMethodName;
	}

	@Override
	public int compareTo(ThunderStat obj) {
        return Long.compare(obj.getCount(), count);
    }

}

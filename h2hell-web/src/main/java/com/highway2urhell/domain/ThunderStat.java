package com.highway2urhell.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ThunderStat implements Comparable<ThunderStat>{
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	@NotNull
	private Long count;
	@NotNull
	@ManyToOne
	private ThunderApp thunderApp;
	@NotNull
	private String pathClassMethodName;
	@NotNull
	private Boolean falsePositive = false;
	private String uri;
	private String httpmethod;
	private Boolean audit;
	private Long averageTime;
	private Boolean checkLaunch = false;

	public Boolean getDrawAnalysis() {
		return drawAnalysis;
	}

	public void setDrawAnalysis(Boolean drawAnalysis) {
		this.drawAnalysis = drawAnalysis;
	}

	private Boolean drawAnalysis = false;

	public Boolean getCheckLaunch() {
		return checkLaunch;
	}

	public void setCheckLaunch(Boolean checkLaunch) {
		this.checkLaunch = checkLaunch;
	}

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

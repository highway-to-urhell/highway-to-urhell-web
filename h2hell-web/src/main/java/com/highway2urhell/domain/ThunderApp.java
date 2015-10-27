package com.highway2urhell.domain;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;


public class ThunderApp implements IdentifiableEntity<String>{
	private String id;
	@NotNull
	private String nameApp;
	@NotNull
	private String token = "NO_TOKEN";
	@NotNull
	private String dateCreation;
	private String urlApp;
	private String description;
	private String pathSource;
	private String typeAppz;
	private Set<ThunderStat> thunderStatSet = new LinkedHashSet<ThunderStat>();
	private String versionApp;
	private Integer numberEntryPoints;
	private Boolean analysis = false;

	public Boolean getAnalysis() {
		return analysis;
	}

	public void setAnalysis(Boolean analysis) {
		this.analysis = analysis;
	}


	public String getTypeAppz() {
		return typeAppz;
	}

	public void setTypeAppz(String typeAppz) {
		this.typeAppz = typeAppz;
	}

	public String getVersionApp() {
		return versionApp;
	}

	public void setVersionApp(String versionApp) {
		this.versionApp = versionApp;
	}

	public Set<ThunderStat> getThunderStatSet() {
		return thunderStatSet;
	}

	public void setThunderStatSet(Set<ThunderStat> thunderStatSet) {
		this.thunderStatSet = thunderStatSet;
	}

	public String getPathSource() {
		return pathSource;
	}

	public void setPathSource(String pathSource) {
		this.pathSource = pathSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlApp() {
		return urlApp;
	}

	public void setUrlApp(String urlApp) {
		this.urlApp = urlApp;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameApp() {
		return nameApp;
	}

	public void setNameApp(String nameApp) {
		this.nameApp = nameApp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getNumberEntryPoints() {
		return numberEntryPoints;
	}

	public void setNumberEntryPoints(Integer numberEntryPoints) {
		this.numberEntryPoints = numberEntryPoints;
	}
}

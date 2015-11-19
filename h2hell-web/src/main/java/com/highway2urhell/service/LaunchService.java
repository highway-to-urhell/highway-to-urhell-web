package com.highway2urhell.service;

import com.highway2urhell.dao.ThunderAppDao;
import com.highway2urhell.domain.FilterEntryPath;
import com.highway2urhell.domain.ThunderApp;
import com.highway2urhell.domain.ThunderStat;
import com.highway2urhell.rest.domain.DataAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class LaunchService {
	private static final Logger LOG = LoggerFactory
			.getLogger(LaunchService.class);
	@Inject
	private ThunderAppDao thunderAppDao;
	@Inject
	private ThunderStatService thunderStatService;

	public String findAllPaths(String token) {
		RestTemplate restTemplate = new RestTemplate();
		ThunderApp th = thunderAppDao.findByToken(token);
		if (th == null) {
			return "No config for application with token" + token;
		}
		String url = th.getUrlApp() + "h2h/?paths=true";
		String responseEntity = null;
		LOG.info("Call app {} for find all paths", url);
		try {
			responseEntity = restTemplate
					.postForObject(url, null, String.class);

		} catch (RestClientException r) {
			responseEntity = r.getMessage();
		}
		LOG.info("result call {} for find all paths", responseEntity);
		return responseEntity;
	}


	public String launchAnalysis(DataAnalysis da ) {
		RestTemplate restTemplate = new RestTemplate();
		ThunderApp th = thunderAppDao.findByToken(da.getToken());
		if (th == null) {
			return "No config for application with token" + da.getToken();
		}
		String url = th.getUrlApp() + "h2h/?launch=true";

		FilterEntryPath filter = new FilterEntryPath();
		filter.setFilter(true);
		filter.setClassMethod(true);
		List<String> filterPath = new ArrayList<String>();
		for(ThunderStat ts : da.getListTs()){
			if(ts.getCheckLaunch() && ts.getDrawAnalysis()) {
				LOG.debug(ts.getPathClassMethodName()+"-"+ts.getDrawAnalysis()+"-"+ts.getCheckLaunch());
				filterPath.add(ts.getPathClassMethodName());
			}
		}
		thunderStatService.updateListStat(da.getListTs());

		filter.setListFilter(filterPath);
		String responseEntity = null;
		LOG.info("Call app {} for launch analysis", url);
		try {
			responseEntity = restTemplate
					.postForObject(url, filter, String.class);
			th.setAnalysis(true);
			thunderAppDao.save(th);

		} catch (RestClientException r) {
			responseEntity = r.getMessage();
		}
		LOG.info("result call {} for launch analysis", responseEntity);
		return responseEntity;
	}



}

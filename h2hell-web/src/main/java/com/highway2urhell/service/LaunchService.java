package com.highway2urhell.service;

import com.highway2urhell.dao.ThunderAppDao;
import com.highway2urhell.domain.ThunderApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LaunchService {
	private static final Logger LOG = LoggerFactory
			.getLogger(LaunchService.class);
	@Inject
	private ThunderAppDao thunderAppDao;

	public String launchAnalysis(String token) {
		RestTemplate restTemplate = new RestTemplate();
		ThunderApp th = thunderAppDao.findByToken(token);
		if (th == null) {
			return "No config for application with token" + token;
		}
		String url = th.getUrlApp() + "h2h/?launch=true";
		String responseEntity = null;
		LOG.info("Call app {}", url);
		try {
			responseEntity = restTemplate
					.postForObject(url, null, String.class);
			
		} catch (RestClientException r) {
			responseEntity = r.getMessage();
		}
		LOG.info("result call {}", responseEntity);
		return responseEntity;
	}

}

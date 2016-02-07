package com.highway2urhell.service;

import com.google.gson.Gson;
import com.highway2urhell.repository.EventRepository;
import com.highway2urhell.repository.ThunderAppRepository;
import com.highway2urhell.domain.*;
import com.highway2urhell.domain.DataAnalysis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
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
	private ThunderAppRepository thunderAppRepository;
	@Inject
	private EventRepository eventRepository;
	@Inject
	private ThunderStatService thunderStatService;

	@Transactional
	public String findAllPaths(String token) {
		ThunderApp th = thunderAppRepository.findByToken(token);
		Event ev = new Event();
		ev.setToken(token);
		ev.setTreat(false);
		ev.setTypeMessageEvent(TypeMessageEvent.INIT_PATH);
		//TODO when create reference, next feature
		ev.setReference("NO_REF");
		eventRepository.save(ev);
		//TODO a ameliorer
		return "OK";
	}


	public String launchAnalysis(DataAnalysis da ) {
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
		Gson gson = new Gson();
		String data = gson.toJson(filter);

		Event ev = new Event();
		ev.setToken(da.getToken());
		ev.setTreat(false);
		ev.setTypeMessageEvent(TypeMessageEvent.ENABLE_ENTRY_POINT);
		//TODO when create reference, next feature
		ev.setReference("NO_REF");
		ev.setData(data);
		eventRepository.save(ev);
		//TODO a ameliorer
		return "OK";
	}



}

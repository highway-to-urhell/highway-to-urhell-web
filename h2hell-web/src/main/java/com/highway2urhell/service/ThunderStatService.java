package com.highway2urhell.service;

import com.highway2urhell.repository.BreakerLogRepository;
import com.highway2urhell.repository.MetricsTimerRepository;
import com.highway2urhell.repository.ThunderStatRepository;
import com.highway2urhell.domain.EntryPathData;
import com.highway2urhell.domain.ThunderApp;
import com.highway2urhell.domain.ThunderStat;
import com.highway2urhell.domain.MessageStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@Lazy(false)
public class ThunderStatService {
	private static final Logger LOG = LoggerFactory
			.getLogger(ThunderStatService.class);
	private static List<String> listFrameworkNoAnalysis = new ArrayList<String>(){{
		add("org.apache.struts");
		add("com.google.gwt");
		add("org.springframework");
		add("javax.servlet");
		add("com.sun.faces");
		add("org.apache.activemq");
		add("org.apache.struts2");
		add("org.glassfish.jersey.server");
		add("portAnalysis");
		add("org.springframework.web.struts");
		add("com.highway2urhell.filter");
		add("com.highway2urhell.servlet");
	}};
	@Inject
	private ThunderStatRepository thunderStatRepository;
	@Inject
	private BreakerLogRepository breakerLogRepository;
	@Inject
	private MetricsTimerRepository metricsTimerRepository;

	@Transactional
	public void updateListStat(List<ThunderStat> listTs){
		thunderStatRepository.save(listTs);
	}

	public MessageStat analysisStat(String token){
		MessageStat ms = new MessageStat();
		LOG.info("analysis for token {}",token);
		List<ThunderStat> listThunderStat = thunderStatRepository.findByToken(token);
		LOG.info("SIZE stat for token {}",listThunderStat.size());
		for (ThunderStat ts : listThunderStat) {
			Long count = breakerLogRepository.findByPathClassMethodNameAndToken(
					ts.getPathClassMethodName(), ts.getThunderApp().getToken());
			Long averageTime = metricsTimerRepository.findAverageFromPathClassMethodNameAndToken(ts.getPathClassMethodName(), ts.getThunderApp().getToken());
			ts.setCount(count);
			ts.setAverageTime(averageTime);
		}
		Collections.sort(listThunderStat);
		ms.setListThunderStat(listThunderStat);
		ms.setTotalStat(listThunderStat.size());
		Integer falsePositive = 0;
		Integer totalNoTest = 0;
		for(ThunderStat ts : listThunderStat){
			if(ts.getFalsePositive()){
				falsePositive++;
			}
			if(ts.getCount()==0){
				totalNoTest++;
			}
		}
		ms.setTotalFalsePositive(falsePositive);
		ms.setTotalNoTest(totalNoTest);
		ms.setToken(token);
		return ms;
	}


	@Transactional
	public void createOrUpdateThunderStat(EntryPathData entry,
			ThunderApp ta) {
		String className=entry.getClassName();
		String methodName=entry.getMethodName();
		String uri = entry.getUri();
		String httpmethod=entry.getHttpMethod().name();
		Boolean audit = entry.getAudit();
		String pathClassMethodName = className + "." + methodName;
		ThunderStat ts = thunderStatRepository.findByPathClassMethodNameAndToken(
				pathClassMethodName, ta.getToken());
		if (ts == null) {
			ts = new ThunderStat();
			ts.setPathClassMethodName(pathClassMethodName);
			ts.setThunderApp(ta);
            ts.setCount(0L);
            ts.setHttpmethod(httpmethod);
			ts.setUri(uri);
			ts.setAudit(audit);
			thunderStatRepository.save(ts);
		} else {
            ts.setCount(0L);
        }

	}

	@Transactional
	public void updateThunderStatFalsePositive(String id,String status){
		ThunderStat ts = thunderStatRepository.findOne(id);
		Boolean res = !Boolean.valueOf(status);
		ts.setFalsePositive(res);
		thunderStatRepository.save(ts);
	}

	public void filterFramework(List<ThunderStat> listTS){
		if(listTS!=null && !listTS.isEmpty()){
			for(ThunderStat ts : listTS){
				if(isAframeworkClass(ts.getPathClassMethodName())){
					ts.setDrawAnalysis(false);
				}else{
					ts.setDrawAnalysis(true);
				}
			}
		}
	}

	private Boolean isAframeworkClass(String classMethodName){
		for(String elem : listFrameworkNoAnalysis){
			if(classMethodName.contains(elem)) {
				return true;
			}
		}
		return false;
	}


}

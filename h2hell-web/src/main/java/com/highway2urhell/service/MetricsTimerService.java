package com.highway2urhell.service;

import com.highway2urhell.dao.MetricsTimerDao;
import com.highway2urhell.domain.MessageMetrics;
import com.highway2urhell.domain.MetricsTimer;
import com.highway2urhell.rest.domain.MessageMetricsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Named
public class MetricsTimerService {

	private static final Logger LOG = LoggerFactory
			.getLogger(MetricsTimerService.class);
	
	@Inject
	private MetricsTimerDao metricsTimerDao;


	/**
	 * List MessageMetrics filter by TimeStamp
	 *
	 */


	public void addListPerformance(List<MessageMetrics> listPerf) {
		if (listPerf != null && !listPerf.isEmpty()) {
			for (MessageMetrics msg : listPerf) {
				LOG.info(
						"add indicator Performance for pathClassMethodName {} time {} token {} and dateIncoming {}",
						msg.getPathClassMethodName(), msg.getTimeExec(),
						msg.getToken(), msg.getDateIncoming());
				createMetricsTimer(msg);
			}
		}
	}



	@Transactional
	public MessageMetricsData findMetricsInit(String token){
		List<MetricsTimer> res = metricsTimerDao.findByToken(token);
		MessageMetricsData message = new MessageMetricsData();
		message.setListMetrics(res);
		if(res.size()>0) {
			message.setLastInc(res.get(res.size() - 1).getId());
		}else{
			message.setLastInc(0);
		}
		return message;
	}


	@Transactional
	public MessageMetricsData findMetricsFromlastInc(String token, Integer lastInc){
		if(lastInc == 0){
			return findMetricsInit(token);
		}else {
			List<MetricsTimer> res = metricsTimerDao.findLastInc(token, lastInc);
			LOG.info(" findMetricsFromLastId token " + token + " size " + res.size());
			MessageMetricsData message = new MessageMetricsData();
			if(res.size()>0) {
				message.setLastInc(res.get(res.size() - 1).getId());
				message.setListMetrics(res);
			}else{
				message.setLastInc(lastInc);
			}
			return message;
		}
	}

	@Transactional
	public void createMetricsTimer(MessageMetrics mm) {
		try {
			MetricsTimer mt = new MetricsTimer();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			mt.setDateIncoming(sdf.parse(mm.getDateIncoming()));
			mt.setPathClassMethodName(mm.getPathClassMethodName());
			mt.setTimeExec(mm.getTimeExec());
			mt.setToken(mm.getToken());
			mt.setCpuLoadProcess(mm.getCpuLoadProcess());
			mt.setCpuLoadSystem(mm.getCpuLoadSystem());
			metricsTimerDao.save(mt);
		}catch (ParseException e){
			LOG.error(" Impossible save metrics ",e);
		}
	}

}

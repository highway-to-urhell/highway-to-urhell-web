package com.highway2urhell.service;

import com.highway2urhell.dao.MetricsTimerDao;
import com.highway2urhell.domain.MessageMetrics;
import com.highway2urhell.domain.MetricsTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public void createMetricsTimer(MessageMetrics mm) {
		try {
			MetricsTimer mt = new MetricsTimer();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");
			Date date = new Date();
			mt.setDateIncoming(sdf.parse(mm.getDateIncoming()));
			mt.setPathClassMethodName(mm.getPathClassMethodName());
			mt.setTimeExec(mm.getTimeExec());
			;
			mt.setToken(mm.getToken());
			metricsTimerDao.save(mt);
		}catch (ParseException e){
			LOG.error(" Impossible save metrics ",e);
		}
	}

}

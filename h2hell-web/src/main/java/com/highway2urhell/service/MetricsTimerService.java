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
import java.util.Calendar;
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
	public List<MetricsTimer> findMetricsByDate(String token){
		Date dateEnd = new Date();
		Date dateStart = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateStart);
		cal.add(Calendar.SECOND,-30);
		dateStart = cal.getTime();
		List<MetricsTimer> res = metricsTimerDao.findMetricsBetweenDate(dateStart,dateEnd,token);
		LOG.info(" findmetrics token "+token+" for date "+dateStart + " to "+dateEnd +" size "+res.size());
		return res;
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

package io.highway.to.urhell.service;

import io.highway.to.urhell.dao.MetricsTimerDao;
import io.highway.to.urhell.domain.MessageMetrics;
import io.highway.to.urhell.domain.MetricsTimer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Named
public class MetricsTimerService {

	private static final Logger LOG = LoggerFactory
			.getLogger(MetricsTimerService.class);
	
	@Inject
	private MetricsTimerDao metricsTimerDao;

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
		MetricsTimer mt = new MetricsTimer();
		mt.setDateIncoming(mm.getDateIncoming());
		mt.setPathClassMethodName(mm.getPathClassMethodName());
		mt.setTimeExec(mm.getTimeExec());;
		mt.setToken(mm.getToken());
		metricsTimerDao.save(mt);
	}

}

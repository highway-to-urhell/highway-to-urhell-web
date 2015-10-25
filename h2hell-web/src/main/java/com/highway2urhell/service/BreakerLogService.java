package com.highway2urhell.service;

import com.google.gson.Gson;
import com.highway2urhell.dao.BreakerLogDao;
import com.highway2urhell.dao.ThunderAppDao;
import com.highway2urhell.domain.BreakerLog;
import com.highway2urhell.domain.MessageBreaker;
import com.highway2urhell.domain.ThunderApp;
import com.highway2urhell.exception.exception.DateIncomingException;
import com.highway2urhell.exception.exception.PathNameException;
import com.highway2urhell.exception.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BreakerLogService {
	private static final Logger LOG = LoggerFactory.getLogger(BreakerLogService.class);
	@Inject
	private BreakerLogDao breakerLogDao;
	@Inject
	private ThunderAppDao thunderAppDao;

	@Transactional
	public void createBreakerLog(ThunderApp th, String pathClassMethodName,
			String dateIncoming,String parameters) {
		BreakerLog breaker = new BreakerLog();
		breaker.setDateIncoming(dateIncoming);
		breaker.setPathClassMethodName(pathClassMethodName);
		breaker.setToken(th.getToken());
		breaker.setParameters(parameters);
		breakerLogDao.save(breaker);
	}

	public void addListBreaker(List<MessageBreaker> listBreaker) {
		if (listBreaker != null && !listBreaker.isEmpty()) {
			for (MessageBreaker msg : listBreaker) {
				LOG.info(
						"Call addBreaker with pathClassMethodName {} token {} and dateIncoming {}",
						msg.getPathClassMethodName(), msg.getToken(),
						msg.getDateIncoming());
				addBreaker(msg.getPathClassMethodName(), msg.getToken(),
						msg.getDateIncoming(),msg.getParameters());
			}
		}
	}

	private void addBreaker(String pathClassMethodName, String token,
			String dateIncoming,String parameters) {
		validate(pathClassMethodName, token, dateIncoming);
		ThunderApp th = thunderAppDao.findByToken(token);
        // Add breaker log Service
        createBreakerLog(th, pathClassMethodName, dateIncoming,parameters);
	}

	private void validate(String pathClassMethodName, String token,
			String dateIncoming) {
		if (pathClassMethodName == null) {
			throw new PathNameException();
		}
		if (token == null) {
			throw new TokenException();
		}
		if (dateIncoming == null) {
			throw new DateIncomingException();
		}

	}

}

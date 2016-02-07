package com.highway2urhell.service;

import com.google.gson.Gson;
import com.highway2urhell.repository.BreakerLogRepository;
import com.highway2urhell.repository.ThunderAppRepository;
import com.highway2urhell.domain.BreakerLog;
import com.highway2urhell.domain.LeechParamMethodData;
import com.highway2urhell.domain.MessageBreaker;
import com.highway2urhell.domain.ThunderApp;
import com.highway2urhell.exception.exception.DateIncomingException;
import com.highway2urhell.exception.exception.PathNameException;
import com.highway2urhell.exception.exception.TokenException;
import com.highway2urhell.domain.MessageBreakerLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Named
public class BreakerLogService {
	private static final Logger LOG = LoggerFactory.getLogger(BreakerLogService.class);
	@Inject
	private BreakerLogRepository breakerLogRepository;
	@Inject
	private ThunderAppRepository thunderAppRepository;

	public List<MessageBreakerLog> findByToken(String token){
		List<BreakerLog> listBreaker = breakerLogRepository.findByToken(token);
		List<MessageBreakerLog> res = new ArrayList<MessageBreakerLog>();

		for(BreakerLog breaker : listBreaker) {
			MessageBreakerLog toAdd = new MessageBreakerLog();
			toAdd.setDateIncoming(breaker.getDateIncoming());
			toAdd.setPathClassMethodName(breaker.getPathClassMethodName());
			toAdd.setToken(breaker.getToken());
			//parameters
			String[] tabParameters = breaker.getParameters().split("@@@@@");
			List<LeechParamMethodData> addParam = new ArrayList<LeechParamMethodData>();
			if (tabParameters != null && tabParameters.length > 0) {
				for (int i = 0; i < tabParameters.length; i++) {
					Gson gson = new Gson();
					LeechParamMethodData lpm = gson.fromJson(tabParameters[i], LeechParamMethodData.class);
					addParam.add(lpm);
				}
			}
			toAdd.setParameters(addParam);
			res.add(toAdd);
		}
		return res;
	}

	@Transactional
	public void createBreakerLog(ThunderApp th, String pathClassMethodName,
			String dateIncoming,String parameters) {
		BreakerLog breaker = new BreakerLog();
		breaker.setDateIncoming(dateIncoming);
		breaker.setPathClassMethodName(pathClassMethodName);
		breaker.setToken(th.getToken());
		breaker.setParameters(parameters);
		breakerLogRepository.save(breaker);
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
		ThunderApp th = thunderAppRepository.findByToken(token);
        createBreakerLog(th, pathClassMethodName, dateIncoming,parameters);
	}

	public String extractBreakerWithTokenToJson(String token){
		List<BreakerLog> listbreaker = breakerLogRepository.findByToken(token);
		Gson gson = new Gson();
		return gson.toJson(listbreaker);
	}

	public void extractBreakerWithTokenToCsv(String fileName,String token){
		StringBuilder result = new StringBuilder();
		List<BreakerLog> listbreaker = breakerLogRepository.findByToken(token);
		result.append("token;pathClassMethodName;date;parameters\n");
		for(BreakerLog breaker : listbreaker){
			result.append(breaker.getToken()+";"+breaker.getPathClassMethodName()+";"+breaker.getDateIncoming());
			// add parameters
			String[] tabParameters = breaker.getParameters().split("@@@@@");
			if (tabParameters != null && tabParameters.length > 0) {
				Gson gson = new Gson();
				result.append(gson.toJson(tabParameters));
				result.append("\n");
			}else{
				result.append(" \n");
			}
		}
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.println(result.toString());
			writer.close();
		}catch (Exception e){
			LOG.error(" impossible create file "+fileName+" for token "+token,e);
		}
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

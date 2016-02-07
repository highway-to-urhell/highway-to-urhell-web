package com.highway2urhell.service;

import com.highway2urhell.repository.BreakerLogRepository;
import com.highway2urhell.repository.ThunderAppRepository;
import com.highway2urhell.repository.ThunderStatRepository;
import com.highway2urhell.domain.BreakerLog;
import com.highway2urhell.domain.ThunderApp;
import com.highway2urhell.domain.ThunderStat;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class ThunderAdminService {
	@Inject
	private BreakerLogRepository breakerLogRepository;
	@Inject
	private ThunderStatRepository thunderStatRepository;
	@Inject
	private ThunderAppRepository thunderAppRepository;


	@Transactional
	public void purgeStatByToken(String token) {
		List<ThunderStat> liststat = thunderStatRepository.findByToken(token);
		for(ThunderStat ts : liststat){
            ts.setCount(0L);
            thunderStatRepository.save(ts);
		}
		purgeBreakerByToken(token);
	}

	@Transactional
	public void purgeBreakerByToken(String token) {
		List<BreakerLog> listBreaker = breakerLogRepository.findByToken(token);
		breakerLogRepository.deleteInBatch(listBreaker);
	}

	@Transactional
	public void deleteThunderAppByToken(String token) {
		ThunderApp ta = thunderAppRepository.findByToken(token);
		thunderAppRepository.delete(ta);
		purgeBreakerByToken(token);
		purgeStatByToken(token);

	}



}

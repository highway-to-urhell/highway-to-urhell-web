package com.highway2urhell.service;

import com.highway2urhell.dao.BreakerLogDao;
import com.highway2urhell.dao.ThunderAppDao;
import com.highway2urhell.dao.ThunderStatDao;
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
	private BreakerLogDao breakerLogDao;
	@Inject
	private ThunderStatDao thunderStatDao;
	@Inject
	private ThunderAppDao thunderAppDao;
	

	@Transactional
	public void purgeStatByToken(String token) {
		List<ThunderStat> liststat = thunderStatDao.findByToken(token);
		for(ThunderStat ts : liststat){
            ts.setCount(0L);
            thunderStatDao.save(ts);
		}
		purgeBreakerByToken(token);
	}

	@Transactional
	public void purgeBreakerByToken(String token) {
		List<BreakerLog> listBreaker = breakerLogDao.findByToken(token);
		breakerLogDao.delete(listBreaker);
	}

	@Transactional
	public void deleteThunderAppByToken(String token) {
		ThunderApp ta = thunderAppDao.findByToken(token);
		thunderAppDao.delete(ta);
		purgeBreakerByToken(token);
		purgeStatByToken(token);
		
	}
	
	

}

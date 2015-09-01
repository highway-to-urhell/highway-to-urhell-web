package com.highway2urhell.dao;

import com.highway2urhell.domain.MetricsTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface MetricsTimerDao extends JpaRepository<MetricsTimer, String> {
	
	@Transactional(readOnly = true)
	List<MetricsTimer> findByToken(String token);
	
	@Transactional(readOnly = true)
	@Query("select AVG(mt.timeExec) from MetricsTimer mt where mt.pathClassMethodName=(:pathClassMethodName) and mt.token=(:token)")
	Long findAverageFromPathClassMethodNameAndToken(
			@Param("pathClassMethodName") String pathClassMethodName,
			@Param("token") String token);

	@Transactional(readOnly = true)
	@Query("from MetricsTimer mt where mt.dateIncoming between (:startDate) and (:endDate) AND mt.token=(:token)")
	List<MetricsTimer> findMetricsBetweenDate(@Param("startDate") Date startDate,
											  @Param("endDate") Date endDate,
											  @Param("token") String token);


}

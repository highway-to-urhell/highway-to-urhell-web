package io.highway.to.urhell.dao;

import io.highway.to.urhell.domain.MetricsTimer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MetricsTimerDao extends JpaRepository<MetricsTimer, String> {
	
	@Transactional(readOnly = true)
	List<MetricsTimer> findByToken(String token);
	
	@Transactional(readOnly = true)
	@Query("select AVG(mt.timeExec) from MetricsTimer mt where mt.pathClassMethodName=(:pathClassMethodName) and mt.token=(:token)")
	Long findAverageFromPathClassMethodNameAndToken(
			@Param("pathClassMethodName") String pathClassMethodName,
			@Param("token") String token);


}

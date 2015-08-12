package com.highway2urhell.dao;

import com.highway2urhell.domain.BreakerLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BreakerLogDao extends JpaRepository<BreakerLog, String> {

	@Transactional(readOnly = true)
	List<BreakerLog> findByToken(String token);

	@Transactional(readOnly = true)
	@Query("select count(bl) from BreakerLog bl where bl.pathClassMethodName=(:pathClassMethodName) and bl.token=(:token)")
	Long findByPathClassMethodNameAndToken(
			@Param("pathClassMethodName") String pathClassMethodName,
			@Param("token") String token);
}

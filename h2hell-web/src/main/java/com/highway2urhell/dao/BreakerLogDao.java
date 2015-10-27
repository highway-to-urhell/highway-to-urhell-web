package com.highway2urhell.dao;

import com.highway2urhell.domain.BreakerLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BreakerLogDao extends MongoRepository<BreakerLog, String> {

	@Transactional(readOnly = true)
	List<BreakerLog> findByToken(String token);

	@Transactional(readOnly = true)
	@Query("{ 'pathClassMethodName': ?0,'token':?1}")
	Long findByPathClassMethodNameAndToken(
			@Param("pathClassMethodName") String pathClassMethodName,
			@Param("token") String token);
}

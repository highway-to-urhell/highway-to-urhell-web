package com.highway2urhell.dao;

import com.highway2urhell.domain.MetricsTimer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MetricsTimerDao extends MongoRepository<MetricsTimer, String> {
	
	@Transactional(readOnly = true)
	List<MetricsTimer> findByToken(String token);
	
	@Transactional(readOnly = true)
	@Query("{'pathClassMethodName' :?0,'token':?1}")
	Long findAverageFromPathClassMethodNameAndToken(
			@Param("pathClassMethodName") String pathClassMethodName,
			@Param("token") String token);


	@Transactional(readOnly = true)
	@Query("{'id' :{$gt :?0},'token':?1}")
	List<MetricsTimer> findLastInc(@Param("token") String token,
								   @Param("lastInc") Integer lastInc);

	//ORDER
	@Transactional(readOnly = true)
	@Query("{'token': ?0 ,'timeExec' : {$gt :?1}} ")
	List<MetricsTimer> findByFilter(@Param("token") String token,
									@Param("responsetime") Integer responsetime,
									Pageable pr);

}

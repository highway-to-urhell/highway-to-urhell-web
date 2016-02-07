package com.highway2urhell.repository;

import com.highway2urhell.domain.MetricsTimer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MetricsTimerRepository extends JpaRepository<MetricsTimer, String> {

	@Transactional(readOnly = true)
	List<MetricsTimer> findByToken(String token);

	@Transactional(readOnly = true)
	@Query("select AVG(mt.timeExec) from MetricsTimer mt where mt.pathClassMethodName=(:pathClassMethodName) and mt.token=(:token)")
	Long findAverageFromPathClassMethodNameAndToken(
			@Param("pathClassMethodName") String pathClassMethodName,
			@Param("token") String token);


	@Transactional(readOnly = true)
	@Query("from MetricsTimer mt where mt.id>(:lastInc) AND mt.token=(:token)")
	List<MetricsTimer> findLastInc(@Param("token") String token,
								   @Param("lastInc") Integer lastInc);

	//triage par date de plus recent au plus vieux avec token comme key puis timeExec > responsetime puis prend les nbitems
	@Transactional(readOnly = true)
	@Query("from MetricsTimer mt where mt.token=(:token) AND mt.timeExec>(:responsetime) order by mt.dateIncoming DESC")
	List<MetricsTimer> findByFilter(@Param("token") String token,
									@Param("responsetime") Integer responsetime,
									Pageable pr);

}

package com.highway2urhell.dao;

import com.highway2urhell.domain.ThunderStat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ThunderStatDao extends MongoRepository<ThunderStat, String> {

	@Transactional(readOnly = true)
	@Query("{ 'pathClassMethodName': ?0, 'thunderApp': {'token': ?1}}")
	ThunderStat findByPathClassMethodNameAndToken(
			@Param("pathClassMethodName") String pathClassMethodName,
			@Param("token") String token);


	@Transactional(readOnly = true)
	@Query("{ 'token' : ?0 } ")
	List<ThunderStat> findByToken(@Param("token") String token);

}

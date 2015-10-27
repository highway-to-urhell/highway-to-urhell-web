package com.highway2urhell.dao;

import com.highway2urhell.domain.ThunderApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ThunderAppDao extends MongoRepository<ThunderApp, String> {

	@Transactional(readOnly = true)
	ThunderApp findByToken(String token);

}

package com.highway2urhell.dao;

import com.highway2urhell.domain.ThunderApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ThunderAppDao extends JpaRepository<ThunderApp, String> {

	@Transactional(readOnly = true)
	ThunderApp findByToken(String token);

}

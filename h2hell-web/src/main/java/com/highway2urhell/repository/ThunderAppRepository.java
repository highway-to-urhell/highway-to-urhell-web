package com.highway2urhell.repository;

import com.highway2urhell.domain.ThunderApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ThunderAppRepository extends JpaRepository<ThunderApp, String> {

	@Transactional(readOnly = true)
	ThunderApp findByToken(String token);

}

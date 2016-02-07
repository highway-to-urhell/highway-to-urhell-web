package com.highway2urhell.repository;

import com.highway2urhell.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {

    @Transactional(readOnly = true)
    @Query("from Event ev where ev.token=(:token) and ev.treat=false")
    List<Event> findEventByToken(@Param("token") String token);
}


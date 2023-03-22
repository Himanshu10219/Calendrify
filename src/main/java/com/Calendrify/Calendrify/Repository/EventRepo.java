package com.Calendrify.Calendrify.Repository;

import com.Calendrify.Calendrify.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Integer> {
    @Query(value = "SELECT * FROM events",nativeQuery = true)
    List<Event> getAllEvents();
}

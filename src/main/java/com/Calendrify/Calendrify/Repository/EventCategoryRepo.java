package com.Calendrify.Calendrify.Repository;

import com.Calendrify.Calendrify.Models.Comment;
import com.Calendrify.Calendrify.Models.Eventcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventCategoryRepo extends JpaRepository<Eventcategory,Integer> {

    @Query(value = "SELECT * FROM `eventcategory`",nativeQuery = true)
    List<Eventcategory> getAllEventCategory();
}

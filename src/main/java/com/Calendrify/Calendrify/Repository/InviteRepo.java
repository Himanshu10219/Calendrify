package com.Calendrify.Calendrify.Repository;

import com.Calendrify.Calendrify.Models.Eventinvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InviteRepo extends JpaRepository<Eventinvite, Integer> {

    Optional<Eventinvite> findbyID(int ID);
    @Query(value = "SELECT * FROM `eventinvite`",nativeQuery = true)
    List<Eventinvite> getalleventinvite();
}

package com.sda.testreservationsystem.repository;

import com.sda.testreservationsystem.model.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
}

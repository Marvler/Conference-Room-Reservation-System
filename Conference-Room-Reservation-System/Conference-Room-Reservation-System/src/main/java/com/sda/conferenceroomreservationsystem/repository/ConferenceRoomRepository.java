package com.sda.conferenceroomreservationsystem.repository;

import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
}

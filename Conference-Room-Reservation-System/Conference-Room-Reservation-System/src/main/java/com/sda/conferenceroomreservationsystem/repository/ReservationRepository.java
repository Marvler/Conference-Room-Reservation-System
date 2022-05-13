package com.sda.conferenceroomreservationsystem.repository;

import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
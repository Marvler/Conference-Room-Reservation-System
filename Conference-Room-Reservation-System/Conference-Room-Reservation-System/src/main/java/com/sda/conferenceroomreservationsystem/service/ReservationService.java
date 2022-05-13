package com.sda.conferenceroomreservationsystem.service;

import com.sda.conferenceroomreservationsystem.mapper.ReservationMapper;
import com.sda.conferenceroomreservationsystem.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
}

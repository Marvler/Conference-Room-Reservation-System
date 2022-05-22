package com.sda.conferenceroomreservationsystem.repository;

import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import com.sda.conferenceroomreservationsystem.model.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;


    @Test
    void findByByConferenceRoomShouldReturnListOfReservationsByRoom() {
        ConferenceRoom conferenceRoomWawel = ConferenceRoom.Builder()
                .withConferenceRoomName("Wawel")
                .build();

        Reservation reservationFirst = Reservation.Builder()
                .withReservationId(2L)
                .withConferenceRoom(conferenceRoomWawel)
                .build();

        Reservation reservationSecond = Reservation.Builder()
                .withConferenceRoom(conferenceRoomWawel)
                .withReservationId(5L)
                .build();

        List<Reservation> reservationsRoomsList = List.of(reservationFirst, reservationSecond);

        ConferenceRoom conferenceRoomSaved = conferenceRoomRepository.save(conferenceRoomWawel);
        List<Reservation> reservationToFindList = reservationRepository.findByConferenceRoom(conferenceRoomWawel);

        assertThat(reservationToFindList).isNotNull();
        assertThat(reservationToFindList.contains(5L));
    }


}
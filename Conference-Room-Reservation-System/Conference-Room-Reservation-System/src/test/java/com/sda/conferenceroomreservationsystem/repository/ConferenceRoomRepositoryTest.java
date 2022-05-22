package com.sda.conferenceroomreservationsystem.repository;

import com.sda.conferenceroomreservationsystem.model.entity.ConferenceRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConferenceRoomRepositoryTest {

    @Autowired
    private ConferenceRoomRepository repository;

    @Test
    void existsByConferenceRoomName() {
        ConferenceRoom room = new ConferenceRoom(null, "pokoj", "1.8", 1, null, 50, 50, new ArrayList<>(), null);
        repository.delete(room);
        repository.save(room);
        assertTrue(repository.existsByConferenceRoomName("pokoj"));
        repository.delete(room);
    }

    @Test
    void existsByConferenceRoomNameNot() {
        ConferenceRoom room = new ConferenceRoom(null, "pokoj", "1.8", 1, null, 50, 50, new ArrayList<>(), null);
        repository.delete(room);
        repository.save(room);
        assertFalse(repository.existsByConferenceRoomName("salka"));
        repository.delete(room);
    }
}
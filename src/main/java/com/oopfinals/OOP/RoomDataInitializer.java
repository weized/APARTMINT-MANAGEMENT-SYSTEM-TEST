package com.oopfinals.OOP;

import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoomDataInitializer implements CommandLineRunner {

    private final RoomRepository roomRepository;

    public RoomDataInitializer(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Predefined rooms
        String[] roomNumbers = {"101", "102", "103", "104", "105", "201", "202", "203", "204", "205", "301", "302", "303", "304", "305"};

        for (String roomNumber : roomNumbers) {
            // Create room with room number, which automatically calculates price and rent
            Room room = new Room(roomNumber, "Room " + roomNumber);  // Setting a default name like "Room 101", "Room 102", etc.

            // Save the room into the database
            roomRepository.save(room);
        }
    }
}

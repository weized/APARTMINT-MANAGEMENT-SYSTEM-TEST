package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Regulation;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.repository.landlordsection.RegulationRepository;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegulationService {

    private final RoomRepository roomRepository;
    private final RegulationRepository regulationRepository;

    public RegulationService(RoomRepository roomRepository, RegulationRepository regulationRepository) {
        this.roomRepository = roomRepository;
        this.regulationRepository = regulationRepository;
    }

    // Add regulation to a specific room
    public void addRegulationToRoom(Long roomId, String description) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));  // Better exception handling
        Regulation regulation = new Regulation();
        regulation.setDescription(description);
        regulation.setRoom(room);
        regulationRepository.save(regulation);
    }

    // Add regulation to all rooms
    public void addRegulationToAllRooms(String description) {
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            Regulation regulation = new Regulation();
            regulation.setDescription(description);
            regulation.setRoom(room);
            regulationRepository.save(regulation);
        }
    }

    public void save(Regulation regulation) {
    }

    // No need for an abstract save method, as it's handled by regulationRepository.save()
}

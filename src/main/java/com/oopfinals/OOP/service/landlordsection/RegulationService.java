package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Regulation;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.repository.landlordsection.RegulationRepository;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegulationService {

    private final RoomRepository roomRepository;


    @Autowired
    private RegulationRepository regulationRepository;

    @Autowired
    private RoomService roomService;

    public RegulationService(RoomRepository roomRepository, RegulationRepository regulationRepository) {
        this.roomRepository = roomRepository;
        this.regulationRepository = regulationRepository;
    }

    // Add regulation to a specific room
    public void addRegulationToRoom(Long roomId, String description) {
        Room room = roomService.getRoomById(roomId);
        if (room != null) {
            Regulation regulation = new Regulation();
            regulation.setRoom(room);
            regulation.setDescription(description);
            regulationRepository.save(regulation);
        }
    }

    public void addRegulationToAllRooms(String description) {
        List<Room> rooms = roomService.getAllRooms();
        for (Room room : rooms) {
            addRegulationToRoom(room.getId(), description);  // Reuse the above method
        }
    }

    public void save(Regulation regulation) {
    }

    // No need for an abstract save method, as it's handled by regulationRepository.save()
}

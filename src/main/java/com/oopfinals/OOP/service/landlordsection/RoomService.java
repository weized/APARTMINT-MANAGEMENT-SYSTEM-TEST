package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.model.landlordmodel.RoomPaymentInfo;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import com.oopfinals.OOP.repository.landlordsection.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Get all room payments from the database
    public List<RoomPaymentInfo> getAllRoomPayments() {
        List<RoomPaymentInfo> roomPayments = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();

        for (Room room : rooms) {
            RoomPaymentInfo roomPaymentInfo = new RoomPaymentInfo();
            roomPaymentInfo.setName(room.getName());
            roomPaymentInfo.setNumberOfTenants(room.getTenants().size()); // assumes getTenants() returns a List
            roomPaymentInfo.setMonthlyRent(room.getMonthlyRent());
            roomPaymentInfo.setMonthlyBill(room.getMonthlyBill());

            roomPaymentInfo.setPaymentStatus(
                    paymentRepository.getPaymentStatusForRoom(room.getId())
            );

            roomPayments.add(roomPaymentInfo);
        }

        return roomPayments;
    }

    // Get all rooms from the repository
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get a room by its ID
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    // Save a room to the database
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    // ✅ NEW: Get all available rooms (e.g., rooms with 0 tenants or marked as available)
    public List<Room> getAvailableRooms() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getTenants().isEmpty()) // or use room.isAvailable() if such a field exists
                .collect(Collectors.toList());
    }
}

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

    /**
     * Returns all room payment info with tenant count, rent, bill, and payment status.
     */
    public List<RoomPaymentInfo> getAllRoomPayments() {
        List<RoomPaymentInfo> roomPayments = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();

        for (Room room : rooms) {
            RoomPaymentInfo roomPaymentInfo = new RoomPaymentInfo();
            roomPaymentInfo.setRoomNumber(room.getRoomNumber());
            roomPaymentInfo.setNumberOfTenants(room.getTenants().size());
            roomPaymentInfo.setMonthlyRent(room.getRent());  // renamed from getMonthlyRent()
            roomPaymentInfo.setMonthlyBill(0.0); // Placeholder, update if monthlyBill exists

            // If your paymentRepository supports this method
            roomPaymentInfo.setPaymentStatus(
                    paymentRepository.getPaymentStatusForRoom(room.getId())
            );

            roomPayments.add(roomPaymentInfo);
        }

        return roomPayments;
    }

    /**
     * Returns a list of all rooms.
     */
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * Returns a specific room by its ID.
     */
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    /**
     * Saves or updates a room entity.
     */
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    /**
     * Returns rooms that currently have no tenants (available).
     */
    public List<Room> getAvailableRooms() {
        return roomRepository.findAll().stream()
                .filter(room -> room.getTenants() == null || room.getTenants().isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Temporary method to return dummy room data (replace with real logic if needed).
     */
    public Room getRoomDetails() {
        Room room = new Room();
        room.setId(1L);
        room.setRoomNumber("101");
        room.setRent(12000.0);
        return room;
    }

    public Room getRoomByRoomNumber(String roomNumber) {
        return null;
    }
}

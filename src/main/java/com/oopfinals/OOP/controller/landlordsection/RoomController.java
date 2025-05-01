package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.service.landlordsection.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/landlord")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Display the list of rooms
    @GetMapping("/rooms")
    public String showRooms(Model model) {
        // Retrieve the list of rooms and add it to the model
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms); // Corrected this method
        return "landlord/rooms"; // This should render the rooms page
    }

    // Handle the creation of a new room
    @PostMapping("/rooms")
    public String createRoom(@RequestParam("capacity") int capacity,
                             @RequestParam("maxTenants") int maxTenants,
                             @RequestParam("monthlyRent") double monthlyRent,
                             @RequestParam("name") String name,
                             @RequestParam("price") double price,
                             @RequestParam("roomNumber") String roomNumber,
                             Model model) {

        // Validate input
        if (name.isEmpty() || roomNumber.isEmpty() || price <= 0) {
            model.addAttribute("error", "All fields are required and price must be positive!");
            return "landlord/rooms";
        }

        // Create and save the room
        Room room = new Room();
        room.setCapacity(capacity);
        room.setMaxTenants(maxTenants);
        room.setMonthlyRent(monthlyRent);
        room.setName(name);
        room.setPrice(price);  // Ensure price is set
        room.setRoomNumber(roomNumber);

        try {
            roomService.saveRoom(room);
            model.addAttribute("successMessage", "Room created successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while creating the room.");
            e.printStackTrace();
        }

        // Redirect or forward to appropriate page
        return "redirect:/landlord/rooms";
    }
}

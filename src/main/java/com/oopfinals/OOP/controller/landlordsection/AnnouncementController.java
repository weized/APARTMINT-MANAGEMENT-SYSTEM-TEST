package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import com.oopfinals.OOP.service.landlordsection.AnnouncementService;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.service.landlordsection.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private RoomService roomService;

    // Display the announcements page
    @GetMapping("/landlord/announcements")
    public String getAnnouncements(Model model) {
        // Fetch all announcements
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        model.addAttribute("announcements", announcements);

        // Fetch all rooms (for the target dropdown)
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);

        return "landlord/announcements";
    }

    // Handle announcement creation
    @PostMapping("/landlord/announcements")
    public String createAnnouncement(@RequestParam("description") String description,
                                     @RequestParam("target") String target,
                                     Model model) {

        // Validation: Check if description and target are not empty
        if (description.isEmpty() || target.isEmpty()) {
            model.addAttribute("error", "Both fields are required!");
            return "landlord/announcements";
        }

        // Optional: Check if the target is a valid room or "all"
        List<Room> rooms = roomService.getAllRooms();
        boolean validTarget = "all".equals(target);
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(target)) {
                validTarget = true;
                break;
            }
        }

        if (!validTarget) {
            model.addAttribute("error", "Invalid target specified!");
            return "landlord/announcements";
        }

        // Create and save the announcement
        Announcement announcement = new Announcement();
        announcement.setDescription(description);
        announcement.setTarget(target);

        try {
            announcementService.saveAnnouncement(announcement);
            model.addAttribute("successMessage", "Announcement created successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while creating the announcement.");
            e.printStackTrace();
        }

        // Redirect to the announcements page after saving
        return "redirect:/landlord/announcements";
    }

}

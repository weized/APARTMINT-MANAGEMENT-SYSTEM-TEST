package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.service.landlordsection.AnnouncementService;
import com.oopfinals.OOP.service.landlordsection.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/landlord/announcements")
    public String getAnnouncements(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("announcements", announcements);
        model.addAttribute("rooms", rooms);
        return "landlord/announcements";
    }

    @PostMapping("/landlord/announcements")
    public String createAnnouncement(@RequestParam("description") String description,
                                     @RequestParam("target") String target,
                                     RedirectAttributes redirectAttributes) {

        if (description.isEmpty() || target.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Both fields are required!");
            return "redirect:/landlord/announcements";
        }

        List<Room> rooms = roomService.getAllRooms();
        boolean validTarget = "all".equals(target);
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(target)) {
                validTarget = true;
                break;
            }
        }

        if (!validTarget) {
            redirectAttributes.addFlashAttribute("error", "Invalid target specified!");
            return "redirect:/landlord/announcements";
        }

        Announcement announcement = new Announcement();
        announcement.setDescription(description);
        announcement.setTarget(target);

        try {
            announcementService.saveAnnouncement(announcement);
            redirectAttributes.addFlashAttribute("successMessage", "Announcement created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while creating the announcement.");
        }

        return "redirect:/landlord/announcements";
    }

    // ✅ Changed from @DeleteMapping to @PostMapping to work with HTML form
    @PostMapping("/landlord/announcements/{id}")
    public String deleteAnnouncement(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            announcementService.deleteAnnouncement(id);
            redirectAttributes.addFlashAttribute("successMessage", "Announcement deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete announcement.");
        }
        return "redirect:/landlord/announcements";
    }
}

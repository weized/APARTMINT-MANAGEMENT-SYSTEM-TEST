package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.model.landlordmodel.Tenant;
import com.oopfinals.OOP.service.landlordsection.RegulationService;
import com.oopfinals.OOP.service.landlordsection.RoomService;
import com.oopfinals.OOP.service.landlordsection.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/landlord")
public class ApartmentInfoController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RegulationService regulationService;

    @Autowired
    private TenantService tenantService;

    @GetMapping("/apartment-info")
    public String showApartmentInfo(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "landlord/apartment";
    }

    @PostMapping("/add-regulation")
    public String addRegulation(@RequestParam("roomId") String roomId,
                                @RequestParam("description") String description, Model model) {

        // Validate description
        if (description == null || description.trim().isEmpty()) {
            model.addAttribute("error", "Regulation description cannot be empty.");
            return "redirect:/landlord/apartment-info";
        }

        try {
            if ("all".equals(roomId)) {
                regulationService.addRegulationToAllRooms(description);
            } else {
                Long roomIdLong = Long.parseLong(roomId);
                regulationService.addRegulationToRoom(roomIdLong, description);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid room ID.");
            return "redirect:/landlord/apartment-info";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while adding the regulation.");
            return "redirect:/landlord/apartment-info";
        }

        return "redirect:/landlord/apartment-info";
    }

    @GetMapping("/landlord/room/{id}/tenants")
    public String getRoomTenants(@PathVariable("id") Long roomId, Model model) {
        // Fetch room and its tenants from the service layer
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("tenants", room.getTenants());
        return "landlord/room-tenants"; // Return the name of the view to display tenants
    }
    @GetMapping("/landlord/room-tenants/{roomId}/tenants")
    public String viewTenantsByRoom(@PathVariable Long roomId, Model model) {

        TenantService tenantService = null;
        List<Tenant> tenants = tenantService.getTenantsByRoomId(roomId); // Implement this method
        model.addAttribute("tenants", tenants);
        return "landlord/tenants-list"; // Ensure this template exists in templates folder
    }

    @GetMapping("/room-tenants/{roomId}/tenants")
    public String getTenantsByRoom(@PathVariable("roomId") Long roomId, Model model) {
        List<Tenant> tenants = tenantService.getTenantsByRoomId(roomId);
        model.addAttribute("tenants", tenants);
        return "landlord/tenants-list"; // This refers to src/main/resources/templates/tenants-list.html
    }
}


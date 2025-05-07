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

    // Show apartment info (list of rooms)
    @GetMapping("/apartment-info")
    public String showApartmentInfo(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "landlord/apartment";
    }

    // Add regulation to all rooms or a specific room
    @PostMapping("/add-regulation")
    public String addRegulation(@RequestParam("roomId") String roomId,
                                @RequestParam("description") String description, Model model) {
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
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while adding the regulation.");
        }

        return "redirect:/landlord/apartment-info";
    }

    @GetMapping("/room-tenants/{roomId}/tenants")
    public String viewTenantsByRoom(@PathVariable Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        List<Tenant> tenants = tenantService.getTenantsByRoomId(roomId);
        model.addAttribute("tenants", tenants);
        model.addAttribute("room", room);
        model.addAttribute("tenant", new Tenant()); // Required for form binding
        return "landlord/tenants-list";
    }

    @PostMapping("/tenants/add")
    public String addTenant(@ModelAttribute Tenant tenant, @RequestParam("roomId") Long roomId) {
        tenantService.addTenantToRoom(tenant, roomId);
        return "redirect:/landlord/room-tenants/" + roomId + "/tenants";
    }
}

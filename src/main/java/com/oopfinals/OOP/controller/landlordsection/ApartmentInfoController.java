package com.oopfinals.OOP.controller.landlordsection;


import com.oopfinals.OOP.model.LeaveRequest;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.model.landlordmodel.Tenant;
import com.oopfinals.OOP.service.LeaveRequestService;
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
    @Autowired
    private LeaveRequestService leaveRequestService;


    @GetMapping("/apartment-info")
    public String showApartmentInfo(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        List<LeaveRequest> leaveRequests = leaveRequestService.findAllRequests();

        model.addAttribute("rooms", rooms);
        model.addAttribute("leaveRequests", leaveRequests);
        return "landlord/apartment";  // Points to your HTML
    }

    // Add regulation to all rooms or a specific room
    @PostMapping("/add-regulation")
    public String addRegulation(@RequestParam("roomId") String roomId,
                                @RequestParam("description") String description, Model model) {
        if (description == null || description.trim().isEmpty()) {
            model.addAttribute("error", "Regulation description cannot be empty.");
            return "landlord/apartment"; // Return to the same page to show error message
        }

        try {
            if ("all".equals(roomId)) {
                regulationService.addRegulationToAllRooms(description);
            } else {
                Long roomIdLong = Long.parseLong(roomId);
                regulationService.addRegulationToRoom(roomIdLong, description);
            }
            model.addAttribute("success", "Regulation added successfully.");
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid room ID.");
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while adding the regulation.");
        }

        return "redirect:/landlord/apartment-info"; // Stay on the page and show feedback
    }

    // View tenants by room
    @GetMapping("/room-tenants/{roomId}/tenants")
    public String viewTenantsByRoom(@PathVariable Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        List<Tenant> tenants = tenantService.getTenantsByRoomId(roomId);
        model.addAttribute("tenants", tenants);
        model.addAttribute("room", room);
        model.addAttribute("tenant", new Tenant());
        return "landlord/tenants-list";
    }

    // Add a tenant to a room
    @PostMapping("/tenants/add")
    public String addTenant(@ModelAttribute Tenant tenant, @RequestParam("roomId") Long roomId) {
        tenantService.addTenantToRoom(tenant, roomId);
        return "redirect:/landlord/room-tenants/" + roomId + "/tenants";
    }
    // Approve leave request
    @PostMapping("/leave-request/approve")
    public String approveLeaveRequest(@RequestParam Long requestId) {
        leaveRequestService.approveLeaveRequest(requestId);
        return "redirect:/landlord/apartment-info";  // Redirect back to the leave requests page
    }

    // Disapprove leave request
    @PostMapping("/leave-request/disapprove")
    public String disapproveLeaveRequest(@RequestParam Long requestId) {
        leaveRequestService.disapproveLeaveRequest(requestId);
        return "redirect:/landlord/apartment-info";  // Redirect back to the leave requests page
    }


}

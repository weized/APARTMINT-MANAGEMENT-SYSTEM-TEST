package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Regulation;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.model.landlordmodel.Tenant;
import com.oopfinals.OOP.service.landlordsection.RoomService;
import com.oopfinals.OOP.service.landlordsection.TenantService;
import com.oopfinals.OOP.service.landlordsection.RegulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LandlordApartmentController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private RegulationService regulationService;

    // Existing method
    @GetMapping("/landlord/apartment")
    public String showApartmentPage(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "landlord/apartment"; // templates/landlord/apartment.html
    }

    @GetMapping("/landlord/view-tenants")
    public String viewAllTenants(Model model) {
        List<Tenant> tenants = tenantService.getAllTenants();
        model.addAttribute("tenants", tenants);
        return "landlord/view-tenants"; // view-tenants.html page
    }

    // Show add regulation form
    @GetMapping("/landlord/rooms/regulations")
    public String showAddRegulationForm(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("regulation", new Regulation());
        return "landlord/add-regulation"; // templates/landlord/add-regulation.html
    }

    // Handle form submission for regulation
    @PostMapping("/landlord/rooms/regulations")
    public String addRegulation(@ModelAttribute Regulation regulation, @RequestParam Long roomId) {
        Room room = roomService.getRoomById(roomId);
        regulation.setRoom(room);
        regulationService.save(regulation);
        return "redirect:/landlord/apartment";
    }



}

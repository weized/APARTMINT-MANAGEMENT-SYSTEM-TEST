package com.oopfinals.OOP.controller.tenantsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import com.oopfinals.OOP.model.landlordmodel.Complaint;
import com.oopfinals.OOP.model.landlordmodel.Room; // Assuming Room is in landlordmodel
import com.oopfinals.OOP.service.landlordsection.AnnouncementService;
import com.oopfinals.OOP.service.landlordsection.ComplaintService; // Assuming you have ComplaintService
import com.oopfinals.OOP.service.landlordsection.RoomService; // Assuming you have RoomService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TenantController {

    @Autowired
    private ComplaintService complaintService; // Assuming a service to handle complaints

    @Autowired
    private RoomService roomService; // Service to fetch room details for complaints
    @Autowired
    private AnnouncementService announcementService;
    // Home page for tenant
    @GetMapping("/tenant-index")
    public String tenantIndex() {
        return "tenant/tenant-index";
    }

    // Display announcements for tenant
    @GetMapping("/tenant-announcement")
    public String showAnnouncement(Model model) {
        // Get all announcements (implement AnnouncementService as needed)
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("announcements", announcements);
        model.addAttribute("rooms", rooms);
        return "tenant/tenant-announcement";
    }

    @GetMapping("/tenant-reports")
    public String tenantReports(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "tenant/tenant-reports";
    }

    @PostMapping("/tenant/submit-complaint")
    public String submitComplaint(@RequestParam String tenantName,
                                  @RequestParam String description,
                                  @RequestParam Long roomId) {
        Room room = roomService.getRoomById(roomId);
        Complaint complaint = new Complaint(tenantName, description, room);
        complaintService.saveComplaint(complaint);
        return "redirect:/tenant-reports";
    }

    // Tenant's complaints page to view all complaints
    @GetMapping("/tenant/complaints")
    public String viewTenantComplaints(Model model) {
        List<Complaint> complaints = complaintService.getComplaintsByTenantName("TenantName"); // Filter complaints for the current tenant
        model.addAttribute("complaints", complaints);
        return "tenant/tenant-complaints";
    }

    // Tenant leave request (no change)
    @GetMapping("/tenant-leave")
    public String tenantLeave() {
        return "tenant/tenant-leave";
    }
}

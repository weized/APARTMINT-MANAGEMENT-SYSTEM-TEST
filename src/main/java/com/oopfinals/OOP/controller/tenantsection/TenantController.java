package com.oopfinals.OOP.controller.tenantsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import com.oopfinals.OOP.model.landlordmodel.Complaint;
import com.oopfinals.OOP.model.landlordmodel.Request;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.service.landlordsection.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TenantController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private TenantService tenantService;

    // Tenant home page
    @GetMapping("/tenant-index")
    public String tenantIndex() {
        return "tenant/tenant-index";
    }

    // Announcements page
    @GetMapping("/tenant-announcement")
    public String showAnnouncement(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("announcements", announcements);
        model.addAttribute("rooms", rooms);
        return "tenant/tenant-announcement";
    }

    // Display complaint submission form (for /tenant-reports)
    @GetMapping("/tenant-reports")
    public String tenantReports(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "tenant/tenant-complaint";
    }

    // Additional mapping to support direct /tenant-complaint access
    @GetMapping("/tenant-complaint")
    public String tenantComplaint(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "tenant/tenant-complaint";
    }

    @PostMapping("/tenant/submit-complaint")
    public String submitComplaint(@RequestParam("complaint") String complaintText,
                                  @RequestParam("roomId") Long roomId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String tenantName = auth.getName(); // Current tenant username

        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            return "redirect:/tenant/tenant-complaint?error=RoomNotFound";
        }

        Complaint complaint = new Complaint(tenantName, complaintText, room);
        complaintService.saveComplaint(complaint);
        return "redirect:/tenant/tenant-reports";
    }

    // View own complaints
    @GetMapping("/tenant/complaints")
    public String viewTenantComplaints(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String tenantName = auth.getName();  // Current tenant username
        List<Complaint> complaints = complaintService.getComplaintsByTenantName(tenantName);
        model.addAttribute("complaints", complaints);
        return "tenant/tenant-complaint"; // Updated to tenant-specific view
    }

    // Display leave request form
    @GetMapping("/tenant-leave-request")
    public String tenantLeave(@RequestParam(value = "success", required = false) String success,
                              @RequestParam(value = "error", required = false) String error,
                              Model model) {
        model.addAttribute("leaveRequest", new Request());
        model.addAttribute("rooms", roomService.getAllRooms());
        if (success != null) {
            model.addAttribute("message", "Leave request submitted successfully!");
        }
        if (error != null) {
            model.addAttribute("message", "There was an error with the leave request.");
        }
        return "tenant/tenant-leave";
    }

    // Submit leave request
    @PostMapping("/tenant/submit-leave-request")
    public String submitLeaveRequest(@RequestParam("tenantName") String tenantName,
                                     @RequestParam("reason") String reason,
                                     @RequestParam("targetLeaveDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetLeaveDate,
                                     @RequestParam("roomId") Long roomId) {

        // Build Request entity manually
        Request leaveRequest = new Request();
        leaveRequest.setTenantName(tenantName);
        leaveRequest.setReason(reason);
        leaveRequest.setTargetDate(targetLeaveDate);
        leaveRequest.setApproved(false);

        // Fetch room and set it
        Room room = roomService.getRoomById(roomId); // Ensure this method exists
        leaveRequest.setRoom(room);

        requestService.saveLeaveRequest(leaveRequest);

        return "redirect:/tenant/tenant-leave-request?success";
    }
}

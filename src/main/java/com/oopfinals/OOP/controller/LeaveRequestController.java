package com.oopfinals.OOP.controller;

import com.oopfinals.OOP.model.LeaveRequest;
import com.oopfinals.OOP.service.LeaveRequestService; // <- FIXED: import correct service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tenant")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService; // <- FIXED: declare the service

    // Show the leave request form page
    @GetMapping("/leave-request")
    public String showLeaveRequests(Model model) {
        // Retrieve leave requests (or handle as needed)
        List<LeaveRequest> leaveRequests = leaveRequestService.findAllRequests();
        model.addAttribute("leaveRequests", leaveRequests);
        return "tenant/tenant-leave"; // Make sure this view exists
    }

    // Handle form submission
    @PostMapping("/leave/submit") // <- FIXED: removed duplicate "/tenant" from path
    public String submitLeaveRequest(@ModelAttribute LeaveRequest leaveRequest, Principal principal, RedirectAttributes redirectAttributes) {
        leaveRequest.setTenantName(principal.getName());
        leaveRequestService.save(leaveRequest); // <- FIXED: now resolved
        redirectAttributes.addFlashAttribute("successMessage", "Leave request submitted successfully!");
        return "redirect:/tenant/leave-request"; // make sure this URL has a mapping
    }
}

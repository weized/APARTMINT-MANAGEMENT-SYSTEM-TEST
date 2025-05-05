package com.oopfinals.OOP.controller;

import com.oopfinals.OOP.model.LeaveRequest;
import com.oopfinals.OOP.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Controller
@RequestMapping("/tenant") // Apply base path
public class LeaveRequestController {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    // Show the leave request form page
    @GetMapping("/leave")
    public String showLeavePage(Model model) {
        model.addAttribute("leaveRequest", new LeaveRequest());
        return "tenant/tenant-leave"; // this maps to src/main/resources/templates/tenant/tenant-leave.html
    }

    // Handle form submission
    @PostMapping("/leave/submit")
    public String submitLeaveRequest(
            @RequestParam("targetLeaveDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetLeaveDate,
            @RequestParam("reason") String reason
    ) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setTargetLeaveDate(targetLeaveDate);
        leaveRequest.setReason(reason);

        leaveRequestRepository.save(leaveRequest);

        // After saving, redirect to avoid form resubmission
        return "redirect:/tenant/leave";
    }

}

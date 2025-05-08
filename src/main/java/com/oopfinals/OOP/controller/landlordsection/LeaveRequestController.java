package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Request;
import com.oopfinals.OOP.service.landlordsection.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/landlord")
public class LeaveRequestController {

    @Autowired
    private RequestService requestService;

    // ✅ This maps to /landlord/leave-requests and loads all requests for display
    @GetMapping("/leave-requests")
    public String viewLeaveRequests(Model model) {
        List<Request> leaveRequests = requestService.getAllLeaveRequests();
        model.addAttribute("leave_requests", leaveRequests);  // MUST match the name used in the template
        return "landlord/apartment"; // The Thymeleaf template name
    }

    // ✅ Approve a leave request
    @PostMapping("/leave-request/approve")
    public String approveRequest(@RequestParam("requestId") Long id) {
        requestService.approveRequest(id);
        return "redirect:/landlord/leave-requests";
    }

    // ✅ Disapprove a leave request
    @PostMapping("/leave-request/disapprove")
    public String disapproveRequest(@RequestParam("requestId") Long id) {
        requestService.disapproveRequest(id);
        return "redirect:/landlord/leave-requests";
    }
}

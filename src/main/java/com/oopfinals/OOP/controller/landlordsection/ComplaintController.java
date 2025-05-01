package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Complaint;
import com.oopfinals.OOP.service.landlordsection.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/landlord/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public String viewComplaints(Model model) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        model.addAttribute("complaints", complaints);
        return "landlord/complaints"; // Thymeleaf template name
    }

    @PostMapping("/resolve/{id}")
    public String resolveComplaint(@PathVariable Long id) {
        complaintService.resolveComplaint(id);
        return "redirect:/landlord/complaints";
    }
}

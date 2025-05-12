package com.oopfinals.OOP.controller;

import com.oopfinals.OOP.model.Report;
import com.oopfinals.OOP.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TenantController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/tenant-index")
    public String tenantIndex() {

        return "tenant/tenant-index";  // points to /templates/tenant/tenant-index.html
    }
    @GetMapping("/tenant-announcement")
    public String showAnnouncement(Model model) {
        // Add necessary data to the model
        return "tenant/tenant-announcement";
    }

    @GetMapping("/tenant-payments")
    public String viewPayments(Model model) {
        // Add necessary data to the model
        return "redirect:/tenant-payments/list";
    }
    // Map to the tenant announcements page


    // Map to the tenant reports page
    @GetMapping("/tenant-reports")
    public String tenantReports(Model model) {
        model.addAttribute("report", new Report()); // Make sure Report class exists
        return "tenant/tenant-reports";
    }

    // Map to the tenant leave request page
    @GetMapping("/tenant-leave")
    public String tenantLeave() {
        return "tenant/tenant-leave";  // points to /templates/tenant/tenant-leave.html
    }

    // Sample methods for fetching data (replace with real data fetching logic)
    private Object getAllAnnouncements() {
        // Replace with actual service or data fetching logic
        return new Object();
    }

    private Object getRoomNotices() {
        // Replace with actual service or data fetching logic
        return new Object();
    }

    private Object getRoomRules() {
        // Replace with actual service or data fetching logic
        return new Object();
    }

    private Object getTenantPayments() {
        // Replace with actual service or data fetching logic
        return new Object();
    }

}

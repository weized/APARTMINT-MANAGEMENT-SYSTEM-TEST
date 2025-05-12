package com.oopfinals.OOP.controller;

import com.oopfinals.OOP.model.Report;
import com.oopfinals.OOP.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tenant/reports") // Optional: helps group related paths
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    // Show form
    @GetMapping
    public String showReportForm(Model model) {
        model.addAttribute("report", new Report());
        return "tenant/tenant-reports";
    }


    // Handle form submission
    @PostMapping("")
    public String submitReport(@ModelAttribute Report report, RedirectAttributes redirectAttributes) {
        reportRepository.save(report);
        redirectAttributes.addFlashAttribute("successMessage", "Complaint submitted successfully.");
        return "redirect:/tenant/reports";
    }


    @ModelAttribute("report")
    public Report report() {
        return new Report();
    }

}



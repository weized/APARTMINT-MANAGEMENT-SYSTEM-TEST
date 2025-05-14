package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.Report;
import com.oopfinals.OOP.model.landlordmodel.Complaint;
import com.oopfinals.OOP.service.ReportService;
import com.oopfinals.OOP.service.landlordsection.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/landlord/complaints")
public class ComplaintController {

    private final ReportService reportService;

    @Autowired
    public ComplaintController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String viewComplaints(Model model) {
        List<Report> complaints = reportService.getAllReports();
        model.addAttribute("complaints", complaints);
        return "landlord/complaints"; // Thymeleaf template name
    }


}

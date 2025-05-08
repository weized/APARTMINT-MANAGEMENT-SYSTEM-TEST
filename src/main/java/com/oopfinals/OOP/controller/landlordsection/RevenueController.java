package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.service.landlordsection.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class RevenueController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/landlord/revenue")
    public String viewRevenue(@RequestParam(value = "type", defaultValue = "monthly") String type, Model model) {
        BigDecimal revenue;
        if (type.equals("yearly")) {
            revenue = paymentService.calculateAnnualRevenue();
        } else {
            revenue = paymentService.calculateMonthlyRevenue();
        }

        model.addAttribute("revenue", revenue);
        model.addAttribute("type", type);

        return "landlord/revenue";  // This should match your HTML file
    }
}

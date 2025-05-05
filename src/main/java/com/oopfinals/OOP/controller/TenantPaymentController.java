package com.oopfinals.OOP.controller;

import com.oopfinals.OOP.model.TenantPayment;
import com.oopfinals.OOP.repository.TenantPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tenant-payments")
public class TenantPaymentController {

    @Autowired
    private TenantPaymentRepository paymentRepository;

    // Display list of payments
    @GetMapping("/list")
    public String listPayments(Model model) {
        List<TenantPayment> payments = paymentRepository.findAll();
        model.addAttribute("payments", payments);
        return "tenant/tenant-payments";
    }


    // Toggle status and insert next month if needed
    @PostMapping("/mark-paid/{id}")
    public String markAsPaid(@PathVariable Long id) {
        TenantPayment payment = paymentRepository.findById(id).orElse(null);

        if (payment != null) {
            String currentStatus = payment.getStatus();

            if (currentStatus.equals("Unpaid")) {
                // Mark as Paid
                payment.setStatus("Paid");
                paymentRepository.save(payment);

                // Add next month's payment if not already exists
                LocalDate nextMonthDate = payment.getPaymentDate().plusMonths(1);
                boolean exists = paymentRepository.existsByPaymentDate(nextMonthDate);

                if (!exists) {
                    TenantPayment nextPayment = new TenantPayment();
                    nextPayment.setPaymentDate(nextMonthDate);
                    nextPayment.setAmountPaid(2500.00); // fixed amount
                    nextPayment.setStatus("Unpaid");
                    paymentRepository.save(nextPayment);
                }

            } else {
                // Toggle back to Unpaid
                payment.setStatus("Unpaid");
                paymentRepository.save(payment);
            }
        }

        // Redirect to list page
        return "redirect:/tenant-payments/list";
    }

}


package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Payment;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.service.landlordsection.PaymentService;
import com.oopfinals.OOP.service.landlordsection.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/landlord")
public class PaymentController {

    private final PaymentService paymentService;
    private final RoomService roomService;

    public PaymentController(PaymentService paymentService, RoomService roomService) {
        this.paymentService = paymentService;
        this.roomService = roomService;
    }

    // Display all payments and room payment information
    @GetMapping("/payments")
    public String viewPayments(Model model) {
        // Fetch room payment information (assuming RoomService provides it)
        List<Room> rooms = roomService.getAllRooms(); // Get all rooms
        model.addAttribute("rooms", rooms);  // Add rooms to the model for the view

        // Optionally, fetch all payments or any additional data
        List<Payment> payments = paymentService.getAllPayments();
        model.addAttribute("payments", payments);  // Add all payments to the model

        return "landlord/payments";  // Return the payments view template
    }

    // Add a new payment for a specific room and tenant
    @PostMapping("/payments/add")
    public String addPayment(@RequestParam String tenantName,
                             @RequestParam Double amount,
                             @RequestParam Long roomId) {
        // Find the room by its ID
        Room room = roomService.getRoomById(roomId);

        // Create a new Payment object
        Payment payment = new Payment(tenantName, amount, LocalDate.now(), room);  // Payment constructor expects room
        paymentService.savePayment(payment);  // Save the payment

        return "redirect:/landlord/payments";  // Redirect back to payments view
    }
}

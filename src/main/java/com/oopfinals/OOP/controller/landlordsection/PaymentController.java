package com.oopfinals.OOP.controller.landlordsection;

import com.oopfinals.OOP.dto.RoomPaymentDTO;
import com.oopfinals.OOP.model.landlordmodel.Payment;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.service.landlordsection.PaymentService;
import com.oopfinals.OOP.service.landlordsection.RoomService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

    @GetMapping("/payments")
    public String viewPayments(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        List<Payment> payments = paymentService.getAllPayments();

        List<RoomPaymentDTO> roomPayments = new ArrayList<>();

        for (Room room : rooms) {
            Payment latestPayment = payments.stream()
                    .filter(p -> p.getRoom().getId().equals(room.getId()))
                    .findFirst()
                    .orElse(null);

            String status = latestPayment != null ? latestPayment.getPaymentStatus() : "UNPAID";
            double monthlyRent = room.getMonthlyRent();
            double bill = monthlyRent;
            int tenantCount = room.getTenants().size();

            RoomPaymentDTO dto = new RoomPaymentDTO(
                    room.getName(),
                    tenantCount,
                    monthlyRent,
                    bill,
                    status
            );

            roomPayments.add(dto);
        }

        model.addAttribute("roomPayments", roomPayments);
        return "landlord/payments";
    }
}

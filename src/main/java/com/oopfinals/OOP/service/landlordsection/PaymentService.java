package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Payment;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.repository.landlordsection.PaymentRepository;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RoomRepository roomRepository;

    public PaymentService(PaymentRepository paymentRepository, RoomRepository roomRepository) {
        this.paymentRepository = paymentRepository;
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Payment> getPaymentsByRoom(Room room) {
        return paymentRepository.findByRoom(room);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public BigDecimal calculateMonthlyRevenue() {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);

        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startOfMonth, endOfMonth);
        return payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateAnnualRevenue() {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
        LocalDate endOfYear = startOfYear.plusYears(1).minusDays(1);

        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startOfYear, endOfYear);
        return payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

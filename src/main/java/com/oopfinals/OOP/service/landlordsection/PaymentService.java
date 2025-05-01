package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Payment;
import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.repository.landlordsection.PaymentRepository;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RoomRepository roomRepository;

    // Constructor injection for repositories
    public PaymentService(PaymentRepository paymentRepository, RoomRepository roomRepository) {
        this.paymentRepository = paymentRepository;
        this.roomRepository = roomRepository;
    }

    // Method to get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Method to get all payments for a specific room
    public List<Payment> getPaymentsByRoom(Room room) {
        return paymentRepository.findByRoom(room);  // make sure this method exists in PaymentRepository
    }

    // Method to get all payments (returns all payments from the database)
    public List<Payment> getAllPayments() {
        return (List<Payment>) paymentRepository.findAll();  // this is fine as is
    }

    // Method to save a payment to the database
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);  // saving the payment
    }

    // Calculate revenue for the current month
    public double calculateMonthlyRevenue() {
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);  // start of the current month
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);  // end of the current month

        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startOfMonth, endOfMonth);  // getting payments for the month
        return payments.stream().mapToDouble(Payment::getAmount).sum();  // summing the payment amounts
    }

    // Calculate revenue for the current year
    public double calculateAnnualRevenue() {
        LocalDate startOfYear = LocalDate.now().withDayOfYear(1);  // start of the current year
        LocalDate endOfYear = startOfYear.plusYears(1).minusDays(1);  // end of the current year

        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startOfYear, endOfYear);  // getting payments for the year
        return payments.stream().mapToDouble(Payment::getAmount).sum();  // summing the payment amounts
    }
}

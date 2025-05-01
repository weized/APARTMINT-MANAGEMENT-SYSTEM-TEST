package com.oopfinals.OOP.repository.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Payment;
import com.oopfinals.OOP.model.landlordmodel.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    // Query to find payments for a specific room
    List<Payment> findByRoom(Room room);

    // Query to find payments between two dates
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);

    // Query to get the total amount paid for a specific room (can be added if needed)
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.room = :room")
    Double getTotalAmountForRoom(Room room);

    // Query to get the payment status for a specific room by its ID
    @Query("SELECT p.paymentStatus FROM Payment p WHERE p.room.id = :id ORDER BY p.paymentDate DESC")
    String getPaymentStatusForRoom(Long id);
}

package com.oopfinals.OOP.repository.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Payment;
import com.oopfinals.OOP.model.landlordmodel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
    List<Payment> findByRoom(Room room);

    String findPaymentStatusForRoom(Long id);

    String getPaymentStatusForRoom(Long id);
}

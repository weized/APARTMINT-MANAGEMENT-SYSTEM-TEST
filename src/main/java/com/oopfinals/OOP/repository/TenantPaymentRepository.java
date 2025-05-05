package com.oopfinals.OOP.repository;

import com.oopfinals.OOP.model.TenantPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TenantPaymentRepository extends JpaRepository<TenantPayment, Long> {

    boolean existsByPaymentDate(LocalDate paymentDate);
}

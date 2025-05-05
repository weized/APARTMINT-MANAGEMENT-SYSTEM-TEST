package com.oopfinals.OOP.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tenant_payments")
public class TenantPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount_paid", nullable = false)
    private Double amountPaid = 2500.00;

    @Column(nullable = false)
    private String status = "Unpaid";

    // Constructors
    public TenantPayment() {
    }

    public TenantPayment(LocalDate paymentDate, String status) {
        this.paymentDate = paymentDate;
        this.amountPaid = 2500.00;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

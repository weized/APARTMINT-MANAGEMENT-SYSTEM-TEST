package com.oopfinals.OOP.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenantName;

    private LocalDate targetLeaveDate;
    @Column(name = "status")
    private String status; // Approved, Disapproved, Pending


    @Column(length = 500)
    private String reason;

    // Constructors
    public LeaveRequest() {}

    public LeaveRequest(String tenantName, LocalDate targetLeaveDate, String reason) {
        this.tenantName = tenantName;
        this.targetLeaveDate = targetLeaveDate;
        this.reason = reason;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }
    public String getStatus() {
        return status;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public LocalDate getTargetLeaveDate() {
        return targetLeaveDate;
    }

    public void setTargetLeaveDate(LocalDate targetLeaveDate) {
        this.targetLeaveDate = targetLeaveDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

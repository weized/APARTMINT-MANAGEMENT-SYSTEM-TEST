package com.oopfinals.OOP.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate targetLeaveDate;

    @Column(length = 500)
    private String reason;

    // Constructors
    public LeaveRequest() {}

    public LeaveRequest(LocalDate targetLeaveDate, String reason) {
        this.targetLeaveDate = targetLeaveDate;
        this.reason = reason;
    }

    // Getters and setters
    public Long getId() { return id; }
    public LocalDate getTargetLeaveDate() { return targetLeaveDate; }
    public void setTargetLeaveDate(LocalDate targetLeaveDate) { this.targetLeaveDate = targetLeaveDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}

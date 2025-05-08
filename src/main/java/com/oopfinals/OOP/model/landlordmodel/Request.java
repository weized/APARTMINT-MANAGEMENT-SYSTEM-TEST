package com.oopfinals.OOP.model.landlordmodel;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_name", nullable = false)
    private String tenantName;

    @Column(nullable = false)
    private String reason;

    @Column(name = "target_leave_date", nullable = false)
    private LocalDate targetLeaveDate;

    @Column(nullable = false)
    private Boolean approved = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

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

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getTargetLeaveDate() {
        return targetLeaveDate;
    }

    public void setTargetLeaveDate(LocalDate targetLeaveDate) {
        this.targetLeaveDate = targetLeaveDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setTargetDate(LocalDate targetLeaveDate) {

    }
}

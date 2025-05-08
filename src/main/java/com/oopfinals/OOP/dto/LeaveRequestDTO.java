package com.oopfinals.OOP.dto;

import com.fasterxml.jackson.databind.PropertyName;

import java.time.LocalDate;

public class LeaveRequestDTO {
    private Long id;
    private String tenantName;
    private String roomNumber;
    private String reason;
    private LocalDate targetLeaveDate;

    public void setReason(Object reason) {
    }

    public void setTenantName(PropertyName fullName) {
    }

    public void setRoomNumber(String roomNumber) {
    }

    public void setTargetLeaveDate(Object targetLeaveDate) {
    }

    // Constructor, getters and setters
}

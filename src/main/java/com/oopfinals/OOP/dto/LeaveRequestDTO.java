package com.oopfinals.OOP.dto;

import java.time.LocalDate;

public class LeaveRequestDTO {

    private Long requestId;
    private String tenantName;
    private String reason;
    private LocalDate targetLeaveDate;
    private String status;

    // Constructors
    public LeaveRequestDTO() {
    }

    public LeaveRequestDTO(String tenantName, String reason, LocalDate targetLeaveDate, String status) {
        this.tenantName = tenantName;
        this.reason = reason;
        this.targetLeaveDate = targetLeaveDate;
        this.status = status;
    }


    // Getters and Setters
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

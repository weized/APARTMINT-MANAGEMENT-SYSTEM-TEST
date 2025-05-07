package com.oopfinals.OOP.dto;

public class RoomPaymentDTO {
    private String roomName;
    private int numberOfTenants;
    private double monthlyRent;
    private double monthlyBill;
    private String paymentStatus;

    public RoomPaymentDTO(String roomName, int numberOfTenants, double monthlyRent, double monthlyBill, String paymentStatus) {
        this.roomName = roomName;
        this.numberOfTenants = numberOfTenants;
        this.monthlyRent = monthlyRent;
        this.monthlyBill = monthlyBill;
        this.paymentStatus = paymentStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getNumberOfTenants() {
        return numberOfTenants;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public double getMonthlyBill() {
        return monthlyBill;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}

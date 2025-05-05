package com.oopfinals.OOP.model.landlordmodel;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer capacity = 4;

    @Column(nullable = false)
    private Integer maxTenants = 4;

    @Column(nullable = false)
    private Double monthlyRent = 2500.0;

    @Column(nullable = false)
    private Double rent = 2500.0;

    @Column(nullable = false)
    private String name;

    // Relationships
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tenant> tenants;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Regulation> regulations;

    public Room() {}

    public Room(String roomNumber, String name) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.price = calculatePriceBasedOnRoomNumber(roomNumber);
        this.monthlyRent = this.price;
        this.rent = this.price;
    }

    public Room(String roomNumber, String name, Integer price, Integer maxTenants) {
        this.roomNumber = roomNumber;
        this.name = name;
        this.price = Double.valueOf(price);
        this.maxTenants = maxTenants;
        this.monthlyRent = this.price;
        this.rent = this.price;
    }

    private Double calculatePriceBasedOnRoomNumber(String roomNumber) {
        if (roomNumber.startsWith("10") || roomNumber.startsWith("11")) {
            return 3500.0;
        } else if (roomNumber.startsWith("20") || roomNumber.startsWith("21")) {
            return 3000.0;
        } else if (roomNumber.startsWith("30") || roomNumber.startsWith("31")) {
            return 2700.0;
        }
        return 2500.0;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
        this.price = calculatePriceBasedOnRoomNumber(roomNumber);
        this.monthlyRent = this.price;
        this.rent = this.price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.monthlyRent = price;
        this.rent = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getMaxTenants() {
        return maxTenants;
    }

    public void setMaxTenants(Integer maxTenants) {
        this.maxTenants = maxTenants;
    }

    public Double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Double monthlyRent) {
        this.monthlyRent = monthlyRent;
        this.rent = monthlyRent;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<Regulation> getRegulations() {
        return regulations;
    }

    public void setRegulations(List<Regulation> regulations) {
        this.regulations = regulations;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlyBill() {
        return 0;
    }
}
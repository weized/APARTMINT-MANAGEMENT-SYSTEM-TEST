package com.oopfinals.OOP.model.landlordmodel;

import jakarta.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenantName;

    private String description;

    private boolean resolved = false;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    // Constructors
    public Complaint() {
    }

    public Complaint(String tenantName, String description, Room room) {
        this.tenantName = tenantName;
        this.description = description;
        this.room = room;
        this.resolved = false;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

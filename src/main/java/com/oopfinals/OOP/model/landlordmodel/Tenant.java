package com.oopfinals.OOP.model.landlordmodel;

import com.oopfinals.OOP.model.landlordmodel.Room;
import jakarta.persistence.*;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // From tenantmodel
    private String username;
    private String roomNumber;
    private String firstnameName;
    private String middleName;
    private String lastName;
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean filedLeave;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    // Constructors
    public Tenant() {}

    public Tenant(String username, String firstnameName, String middleName, String lastName,
                  String email, String phoneNumber, String roomNumber, boolean filedLeave, Room room) {
        this.username = username;
        this.firstnameName = firstnameName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
        this.filedLeave = filedLeave;
        this.room = room;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getFirstnameName() {
        return firstnameName;
    }

    public void setFirstnameName(String firstnameName) {
        this.firstnameName = firstnameName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isFiledLeave() {
        return filedLeave;
    }

    public void setFiledLeave(boolean filedLeave) {
        this.filedLeave = filedLeave;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

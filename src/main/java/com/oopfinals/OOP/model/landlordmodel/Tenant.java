package com.oopfinals.OOP.model.landlordmodel;

import jakarta.persistence.*;

@Entity
@Table(name = "tenant")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String roomNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean filedLeave;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(nullable = false)
    private boolean leaveApproved = false;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean requestingLeave = false;

    // Constructors
    public Tenant() {}

    public Tenant(String username, String firstName, String middleName, String lastName,
                  String email, String phoneNumber, String roomNumber,
                  boolean filedLeave, Boolean leaveApproved, Room room) {
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
        this.filedLeave = filedLeave;
        this.leaveApproved = leaveApproved != null ? leaveApproved : false;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Boolean getLeaveApproved() {
        return leaveApproved;
    }

    public void setLeaveApproved(Boolean leaveApproved) {
        this.leaveApproved = leaveApproved;
    }

    public Boolean getRequestingLeave() {
        return requestingLeave;
    }

    public void setRequestingLeave(Boolean requestingLeave) {
        this.requestingLeave = requestingLeave;
    }

    // ✅ Safe and trimmed full name method
    public String getName() {
        StringBuilder fullName = new StringBuilder();

        if (firstName != null && !firstName.isBlank()) {
            fullName.append(firstName.trim()).append(" ");
        }
        if (middleName != null && !middleName.isBlank()) {
            fullName.append(middleName.trim()).append(" ");
        }
        if (lastName != null && !lastName.isBlank()) {
            fullName.append(lastName.trim());
        }

        return fullName.toString().trim();
    }

    public String getFullName() {
        return "";
    }
}

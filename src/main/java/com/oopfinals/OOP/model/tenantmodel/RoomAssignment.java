package com.oopfinals.OOP.model.tenantmodel;

import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.model.landlordmodel.Tenant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_assignments") // Name of the table in the database
public class RoomAssignment {

    @Id
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Tenant tenant;

    // You can add more fields if necessary

    // Getters and setters
}

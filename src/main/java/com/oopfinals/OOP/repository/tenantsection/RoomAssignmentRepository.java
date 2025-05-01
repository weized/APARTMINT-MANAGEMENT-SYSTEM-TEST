package com.oopfinals.OOP.repository.tenantsection;

import com.oopfinals.OOP.model.tenantmodel.RoomAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAssignmentRepository extends JpaRepository<RoomAssignment, Long> {
    // You can add custom queries here if necessary
}

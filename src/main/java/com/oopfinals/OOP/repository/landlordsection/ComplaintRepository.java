package com.oopfinals.OOP.repository.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // Custom query to find complaints by tenant's name
    List<Complaint> findByTenantName(String tenantName);
}

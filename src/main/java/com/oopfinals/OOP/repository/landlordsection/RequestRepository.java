package com.oopfinals.OOP.repository.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    // Custom query to find all requests that are not approved
    List<Request> findByApprovedFalse();
}

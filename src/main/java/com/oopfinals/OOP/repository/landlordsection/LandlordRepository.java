package com.oopfinals.OOP.repository.landlordsection; // Ensure this matches the package

import org.springframework.data.jpa.repository.JpaRepository;
import com.oopfinals.OOP.model.landlordmodel.Landlord;

public interface LandlordRepository extends JpaRepository<Landlord, Long> {

    Landlord findByUsername(String username); // Custom query method
}

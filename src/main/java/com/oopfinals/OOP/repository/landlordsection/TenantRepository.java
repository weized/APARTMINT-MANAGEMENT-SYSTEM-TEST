package com.oopfinals.OOP.repository.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByRoomId(Long roomId);

    List<Tenant> findByFiledLeaveTrue();
}



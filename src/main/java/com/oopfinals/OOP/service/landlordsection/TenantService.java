package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Tenant;
import java.util.List;

public interface TenantService {
    List<Tenant> getAllTenants();
    List<Tenant> getTenantsWithFiledLeave();
    List<Tenant> getTenantsByRoomId(Long roomId);
}

package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Tenant;

import java.util.List;

public interface TenantService {
    List<Tenant> getTenantsForRoom(Long roomId);
    List<Tenant> getAllTenants();
    List<Tenant> getTenantsWithFiledLeave();
    List<Tenant> getTenantsByRoomId(Long roomId);
    void addTenant(Tenant tenant, Long roomId);

    void addTenantToRoom(Tenant tenant, Long roomId);
}

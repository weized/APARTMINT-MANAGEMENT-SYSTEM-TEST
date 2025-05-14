package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Tenant;

import java.util.List;

public interface TenantService {

    // Method to fetch tenants by room ID
    List<Tenant> getTenantsForRoom(Long roomId);

    // Method to fetch all tenants
    List<Tenant> getAllTenants();

    // Method to fetch tenants by room ID (duplicate of getTenantsForRoom, can be merged if needed)
    List<Tenant> getTenantsByRoomId(Long roomId);

    // Method to fetch tenants who have filed leave
    List<Tenant> getTenantsWithFiledLeave();

    // Method to fetch tenants who haven't filed leave
    List<Tenant> getTenantsWithoutFiledLeave();  // Added method to return tenants without filed leave

    // Method to add a new tenant and assign them a room
    void addTenant(Tenant tenant, Long roomId);

    // Method to add an existing tenant to a specific room
    void addTenantToRoom(Tenant tenant, Long roomId);

    // Optional: Method to update tenant's leave status
    void updateTenantLeaveStatus(Long tenantId, boolean filedLeave);
}

package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Room;
import com.oopfinals.OOP.model.landlordmodel.Tenant;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import com.oopfinals.OOP.repository.landlordsection.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Tenant> getTenantsForRoom(Long roomId) {
        return tenantRepository.findByRoomId(roomId);
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public List<Tenant> getTenantsWithFiledLeave() {
        return tenantRepository.findByFiledLeaveTrue();
    }

    @Override
    public List<Tenant> getTenantsByRoomId(Long roomId) {
        return tenantRepository.findByRoomId(roomId);
    }

    @Override
    public void addTenant(Tenant tenant, Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

        tenant.setRoom(room);
        tenant.setRoomNumber(room.getRoomNumber());
        tenant.setFiledLeave(false);
        tenantRepository.save(tenant);
    }

    @Override
    public void addTenantToRoom(Tenant tenant, Long roomId) {

    }

    @Override
    public Tenant findByUsername(String username) {
        return null;
    }
}

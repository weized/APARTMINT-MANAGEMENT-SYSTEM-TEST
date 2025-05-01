package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Complaint;
import com.oopfinals.OOP.repository.landlordsection.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    // Get all complaints
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // Resolve a specific complaint by ID
    public void resolveComplaint(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setResolved(true);
        complaintRepository.save(complaint);
    }

    // Get complaints by tenant name (filter complaints by tenant)
    public List<Complaint> getComplaintsByTenantName(String tenantName) {
        return complaintRepository.findByTenantName(tenantName);
    }

    // Save a new complaint to the database
    public void saveComplaint(Complaint newComplaint) {
        complaintRepository.save(newComplaint);
    }
}

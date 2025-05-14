package com.oopfinals.OOP.service;

import com.oopfinals.OOP.model.LeaveRequest;
import com.oopfinals.OOP.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    // ✅ Approve request
    public void approveLeaveRequest(Long requestId) {
        LeaveRequest request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("Approved");
        leaveRequestRepository.save(request);
    }

    // ✅ Disapprove request
    public void disapproveLeaveRequest(Long requestId) {
        LeaveRequest request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus("Disapproved");
        leaveRequestRepository.save(request);
    }

    // ✅ Get all leave requests
    public List<LeaveRequest> findAllRequests() {
        return leaveRequestRepository.findAll();
    }

    // ✅ Save a new request
    public void save(LeaveRequest leaveRequest) {
        leaveRequestRepository.save(leaveRequest);
    }

    // ✅ Optional: filter by landlord if implemented
    public List<LeaveRequest> findAllLeaveRequestsForLandlord(Long landlordId) {
        // Currently returns all, you can filter by landlord if needed later
        return leaveRequestRepository.findAll();
    }
}

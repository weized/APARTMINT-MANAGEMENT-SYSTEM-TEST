package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Request;
import com.oopfinals.OOP.repository.landlordsection.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    // Save a leave request
    public void saveLeaveRequest(Request request) {
        requestRepository.save(request);
    }

    // Get all leave requests
    public List<Request> getAllLeaveRequests() {
        return requestRepository.findAll();
    }

    // Get all pending (unapproved) leave requests
    public List<Request> getAllPendingRequests() {
        return requestRepository.findByApprovedFalse();
    }

    // Approve a leave request
    public boolean approveRequest(Long id) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            request.setApproved(true);
            requestRepository.save(request);
            return true;
        }
        return false;
    }

    // Disapprove a leave request
    public boolean disapproveRequest(Long id) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            request.setApproved(false);
            requestRepository.save(request);
            return true;
        }
        return false;
    }
}

package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import com.oopfinals.OOP.model.landlordmodel.Complaint;
import com.oopfinals.OOP.model.landlordmodel.Landlord;
import com.oopfinals.OOP.repository.landlordsection.AnnouncementRepository;
import com.oopfinals.OOP.repository.landlordsection.ComplaintRepository;
import com.oopfinals.OOP.repository.landlordsection.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandlordService {
    @Autowired
    private LandlordRepository landlordRepository;
    private final ComplaintRepository complaintRepository;
    private final AnnouncementRepository announcementRepository;

    public LandlordService(ComplaintRepository complaintRepository, AnnouncementRepository announcementRepository) {
        this.complaintRepository = complaintRepository;
        this.announcementRepository = announcementRepository;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public void createAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }
    public Long getLandlordIdByUsername(String username) {
        Landlord landlord = (Landlord) landlordRepository.findByUsername(username);  // Assuming findByUsername method exists

        if (landlord != null) {
            return landlord.getId();  // Return the ID of the landlord
        } else {
            throw new RuntimeException("Landlord not found for username: " + username);  // Handle the case where landlord is not found
        }
    }
}

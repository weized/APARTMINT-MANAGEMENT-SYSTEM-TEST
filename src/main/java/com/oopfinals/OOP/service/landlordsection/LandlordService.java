package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import com.oopfinals.OOP.model.landlordmodel.Complaint;
import com.oopfinals.OOP.repository.landlordsection.AnnouncementRepository;
import com.oopfinals.OOP.repository.landlordsection.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandlordService {

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
}

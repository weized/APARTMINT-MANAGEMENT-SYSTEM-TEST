package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import com.oopfinals.OOP.repository.landlordsection.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Long id) {
        if (announcementRepository.existsById(id)) {
            announcementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Announcement not found with id: " + id);
        }
    }
}

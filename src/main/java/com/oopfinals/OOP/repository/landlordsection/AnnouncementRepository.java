package com.oopfinals.OOP.repository.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    // No additional code is needed for deleteById(Long id),
    // as it is inherited from JpaRepository
}

package com.oopfinals.OOP.service.landlordsection;

import com.oopfinals.OOP.model.landlordmodel.Regulation;
import com.oopfinals.OOP.repository.landlordsection.RegulationRepository;
import com.oopfinals.OOP.repository.landlordsection.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegulationServiceImpl extends RegulationService {

    @Autowired
    private RegulationRepository regulationRepository;

    public RegulationServiceImpl(RoomRepository roomRepository, RegulationRepository regulationRepository) {
        super(roomRepository, regulationRepository);
    }

    @Override
    public void save(Regulation regulation) {
        regulationRepository.save(regulation);
    }
}

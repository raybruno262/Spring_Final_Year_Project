package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.SubRegionModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ZoneModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.SubRegionRepository;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.ZoneRepository;

@Service
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private SubRegionRepository subRegionRepository;

    // create new zone
    public ResponseEntity<String> createZone(String name, Boolean isActive, String subRegionId) {
        try {
            if (name == null) {
                return ResponseEntity.ok("Status 3000");
            }
            Optional<ZoneModel> nameExists = zoneRepository.findByNameIgnoreCase(name);

            if (nameExists.isPresent()) {
                return ResponseEntity.ok("Status 5000");
            }

            ZoneModel zone = new ZoneModel();
            if (isActive == null) {
                zone.setIsActive(true);
            } else {
                zone.setIsActive(isActive);
            }
            Optional<SubRegionModel> existingSubRegion = subRegionRepository.findById(subRegionId);
            if (existingSubRegion.isEmpty()) {
                return ResponseEntity.ok("Status 3000");
            }
            SubRegionModel subRegionModel = existingSubRegion.get();

            zone.setName(name);
            zone.setSubRegion(subRegionModel);
            zoneRepository.save(zone);

            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }

    }

}

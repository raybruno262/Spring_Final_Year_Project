package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.RegionModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.SubRegionModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.RegionRepository;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.SubRegionRepository;

@Service
public class SubRegionService {
    @Autowired
    private SubRegionRepository subRegionRepository;
    @Autowired
    private RegionRepository regionRepository;

    // Create SubRegion
    public ResponseEntity<String> createSubRegion(String name, Boolean isActive, String regionId) {
        try {
            if (name == null) {
                return ResponseEntity.ok("Status 3000");
            }
            Optional<SubRegionModel> nameExists = subRegionRepository.findByNameIgnoreCase(name);
            if (nameExists.isPresent()) {
                return ResponseEntity.ok("Status 5000");
            }
            SubRegionModel subRegion = new SubRegionModel();
            if (isActive == null) {
                subRegion.setIsActive(true);
            } else {
                subRegion.setIsActive(isActive);
            }
            Optional<RegionModel> existingRegion = regionRepository.findById(regionId);
            if (existingRegion.isEmpty()) {
                return ResponseEntity.ok("Status 3000");
            }
            RegionModel region = existingRegion.get();

            subRegion.setName(name);
            subRegion.setRegion(region);
            subRegionRepository.save(subRegion);
            return ResponseEntity.ok("Status 1000");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }

    }
}
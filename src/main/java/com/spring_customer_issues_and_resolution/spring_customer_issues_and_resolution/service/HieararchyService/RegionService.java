package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.RegionModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.RegionRepository;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    // Create Region
    public ResponseEntity<String> createRegion(String name, Boolean isActive) {
        try {
            if (name == null) {
                return ResponseEntity.ok("Status 3000");

            }
            Optional<RegionModel> nameExists = regionRepository.findByNameIgnoreCase(name);
            if (nameExists.isPresent()) {
                return ResponseEntity.ok("Status 5000");
            }

            RegionModel region = new RegionModel();
            if (isActive == null) {
                region.setIsActive(true);

            } else {
                region.setIsActive(isActive);
            }
            region.setName(name);
            regionRepository.save(region);
            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }

    }
}

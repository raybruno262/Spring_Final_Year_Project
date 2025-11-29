package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.CountryModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ZoneModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.CountryRepository;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.ZoneRepository;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    // create country
    public ResponseEntity<String> createCountry(String name, Boolean isActive, String zoneId) {
        try {
            if (name == null) {
                return ResponseEntity.ok("Status 3000");
            }
            Optional<CountryModel> nameExists = countryRepository.findByNameIgnoreCase(name);
            if (nameExists.isPresent()) {
                return ResponseEntity.ok("Status 5000");
            }
            CountryModel country = new CountryModel();
            if (isActive == null) {
                country.setIsActive(true);

            } else {
                country.setIsActive(isActive);
            }

            Optional<ZoneModel> existingZone = zoneRepository.findById(zoneId);
            if (existingZone.isEmpty()) {
                return ResponseEntity.ok("Status 3000");
            }
            ZoneModel zone = existingZone.get();

            country.setName(name);
            country.setZone(zone);
            countryRepository.save(country);

            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }

}

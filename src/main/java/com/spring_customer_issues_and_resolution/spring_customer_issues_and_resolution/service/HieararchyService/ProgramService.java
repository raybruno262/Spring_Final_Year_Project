package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.CountryModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ProgramModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ProgramType;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.CountryRepository;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.ProgramRepository;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private CountryRepository countryRepository;

    // create Program
    public ResponseEntity<String> createProgram(String name, Boolean isActive, String countryId,
            String programTypeStr) {
        try {
            ProgramType programType;
            try {
                programType = ProgramType.valueOf(programTypeStr.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.ok("Status 3000");
            }

            if (name == null) {
                return ResponseEntity.ok("Status 3000");
            }
            Optional<ProgramModel> existingProgram = programRepository.findByNameIgnoreCaseAndProgramType(name,
                    programType);

            if (existingProgram.isPresent()) {
                return ResponseEntity.ok("Status 5000");
            }

            ProgramModel program = new ProgramModel();
            if (isActive == null) {
                program.setIsActive(true);
            } else {
                program.setIsActive(isActive);
            }

            Optional<CountryModel> existingCountry = countryRepository.findById(countryId);
            if (existingCountry.isEmpty()) {
                return ResponseEntity.ok("Status 3000");

            }

            CountryModel country = existingCountry.get();

            program.setName(name);
            program.setIsActive(isActive);
            program.setCountry(country);
            program.setProgramType(programType);

            programRepository.save(program);

            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }

}

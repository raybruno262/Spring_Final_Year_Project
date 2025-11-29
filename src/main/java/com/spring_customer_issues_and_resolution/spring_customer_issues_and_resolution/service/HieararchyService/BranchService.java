package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.BranchModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ProgramModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.BranchRepository;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository.ProgramRepository;

@Service

public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private ProgramRepository programRepository;

    public ResponseEntity<String> createBranch(String name, Boolean isActive, String programId) {
        try {

            if (name == null) {
                return ResponseEntity.ok("Status 3000");
            }
            Optional<BranchModel> nameExists = branchRepository.findByNameIgnoreCase(name);
            if (nameExists.isPresent()) {
                return ResponseEntity.ok("Status 5000");
            }
            BranchModel branch = new BranchModel();
            if (isActive == null) {
                branch.setIsActive(true);
            } else {
                branch.setIsActive(isActive);
            }
            Optional<ProgramModel> existingProgram = programRepository.findById(programId);
            if (existingProgram.isEmpty()) {
                return ResponseEntity.ok("Status 3000");
            }
            ProgramModel program = existingProgram.get();

            branch.setName(name);
            branch.setProgram(program);
            branchRepository.save(branch);

            return ResponseEntity.ok("Status 1000");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Status 9999");
        }
    }
}

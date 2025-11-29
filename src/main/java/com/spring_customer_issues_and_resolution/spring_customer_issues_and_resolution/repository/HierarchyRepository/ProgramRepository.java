package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ProgramModel;
import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ProgramType;

public interface ProgramRepository extends MongoRepository<ProgramModel, String> {
    // check if name already exists
    Optional<ProgramModel> findByNameIgnoreCaseAndProgramType(String name, ProgramType programType);

}

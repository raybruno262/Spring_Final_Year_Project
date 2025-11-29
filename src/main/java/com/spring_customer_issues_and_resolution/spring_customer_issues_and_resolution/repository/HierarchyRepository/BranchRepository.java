package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.BranchModel;

public interface BranchRepository extends MongoRepository<BranchModel, String> {
    // check is name already exists
    Optional<BranchModel> findByNameIgnoreCase(String name);
}

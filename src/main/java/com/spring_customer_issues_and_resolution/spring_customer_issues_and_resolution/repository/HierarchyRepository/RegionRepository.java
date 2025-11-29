package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.RegionModel;

public interface RegionRepository extends MongoRepository<RegionModel, String> {
    // find region by name
    Optional<RegionModel> findByNameIgnoreCase(String name);
}

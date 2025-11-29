package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.SubRegionModel;

public interface SubRegionRepository extends MongoRepository<SubRegionModel, String> {

    // find existing subRegion
    Optional<SubRegionModel> findByNameIgnoreCase(String name);

}

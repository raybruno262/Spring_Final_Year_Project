package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.ZoneModel;

public interface ZoneRepository extends MongoRepository<ZoneModel, String> {
    // check if the zone name already exists
    Optional<ZoneModel> findByNameIgnoreCase(String name);

}

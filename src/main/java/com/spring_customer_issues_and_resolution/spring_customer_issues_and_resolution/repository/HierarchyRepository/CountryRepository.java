package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.repository.HierarchyRepository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.model.HierarchyModel.CountryModel;

public interface CountryRepository extends MongoRepository<CountryModel, String> {
    // find if name already exists
    Optional<CountryModel> findByNameIgnoreCase(String name);
}

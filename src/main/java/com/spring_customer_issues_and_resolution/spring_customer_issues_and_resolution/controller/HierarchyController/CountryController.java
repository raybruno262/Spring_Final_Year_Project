package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.controller.HierarchyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/createCountry")
    public ResponseEntity<String> createCountry(@RequestParam String name,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam String zoneId) {
        return countryService.createCountry(name, isActive, zoneId);

    }

}

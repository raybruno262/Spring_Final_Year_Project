package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.controller.HierarchyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService.RegionService;

@RestController
@RequestMapping("api/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/createRegion")
    public ResponseEntity<String> createRegion(@RequestParam String name,
            @RequestParam(required = false) Boolean isActive) {
        return regionService.createRegion(name, isActive);
    }

}

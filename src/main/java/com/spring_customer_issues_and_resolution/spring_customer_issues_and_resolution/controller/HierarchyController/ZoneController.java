package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.controller.HierarchyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService.ZoneService;

@RestController
@RequestMapping("api/zone")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    @PostMapping("/createZone")
    public ResponseEntity<String> createZone(@RequestParam String name,
            @RequestParam(required = false) Boolean isActive, @RequestParam String subRegionId) {
        return zoneService.createZone(name, isActive, subRegionId);
    }

}

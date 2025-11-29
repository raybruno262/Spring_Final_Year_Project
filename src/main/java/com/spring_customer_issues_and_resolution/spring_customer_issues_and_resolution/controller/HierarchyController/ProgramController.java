package com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.controller.HierarchyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_customer_issues_and_resolution.spring_customer_issues_and_resolution.service.HieararchyService.ProgramService;

@RestController
@RequestMapping("/api/program")
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @PostMapping("/createProgram")
    public ResponseEntity<String> createProgram(@RequestParam String name,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam String countryId, @RequestParam String programType) {
        return programService.createProgram(name, isActive, countryId, programType);
    }

}

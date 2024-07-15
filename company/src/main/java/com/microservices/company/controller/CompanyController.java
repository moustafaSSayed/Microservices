package com.microservices.company.controller;

import com.microservices.company.dto.CompanyWithReviewsDTO;
import com.microservices.company.model.Company;
import com.microservices.company.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyWithReviewsDTO>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyWithReviewsDTO> getCompanyById(@PathVariable Long id) {
        CompanyWithReviewsDTO company = companyService.findCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return ResponseEntity.ok("company created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @RequestBody Company company) {
        if(companyService.updateCompany(id,company) == true) {
            return ResponseEntity.ok("company updated successfully!");
        }
        return new ResponseEntity<>("No Matching company id",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        if (companyService.deleteCompany(id)) {
            return ResponseEntity.ok("company deleted successfully!");
        }
        return new ResponseEntity<>("No Matching company id",HttpStatus.NOT_FOUND);
    }
}

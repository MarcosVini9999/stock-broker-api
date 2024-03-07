package com.mandacarubroker.elements.controller;


import com.mandacarubroker.elements.domain.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.domain.dtos.ResponseCompanyDTO;
import com.mandacarubroker.elements.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/elements/companies")
public class CompanyController {
    private final CompanyService companyService;
    @Autowired
    public  CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }



    @GetMapping
    public ResponseEntity<List<ResponseCompanyDTO>>getAllStocks() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ResponseCompanyDTO>  getCompanyById(@PathVariable String id) {
        return ResponseEntity.ok(companyService.getById(id));

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editCompany(@PathVariable String id, @RequestBody RequestCompanyDTO data) {
        companyService.validateAndUpdateCompany(id, data);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Stock update successfully");
    }

    @PostMapping("/companyAdd")
    public ResponseEntity<String> addCompany(@RequestBody RequestCompanyDTO data) {
        companyService.validateAndCreateCompany(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Company created successfully");

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        companyService.delete(id);
        return ResponseEntity.ok("Company deleted successfully");
    }
}


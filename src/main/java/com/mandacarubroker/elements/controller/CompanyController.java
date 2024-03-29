package com.mandacarubroker.elements.controller;


import com.mandacarubroker.elements.domain.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.domain.dtos.ResponseCompanyDTO;
import com.mandacarubroker.elements.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elements/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<ResponseCompanyDTO>>getAllStocks() {
        return ResponseEntity.ok(companyService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCompanyDTO> getCompany(@PathVariable String id) {
        ResponseCompanyDTO responseCompanyDTO = companyService.getById(id);
        return ResponseEntity.ok(responseCompanyDTO);
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<ResponseCompanyDTO>  getCompanyById(@PathVariable String id) {
        return ResponseEntity.ok(companyService.getById(id));

    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCompany(@PathVariable String id, @RequestBody RequestCompanyDTO data) {
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


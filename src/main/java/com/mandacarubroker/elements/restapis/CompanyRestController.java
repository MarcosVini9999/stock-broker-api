package com.mandacarubroker.elements.restapis;

import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyRestController {

    @Autowired
    private CompanyService companyService;

    @CrossOrigin
    @GetMapping("/api/elements/companies")
    public List<Company>  getAll(Model model){
        List<Company> companies =   companyService.findAll();
        return companies;
    }
}

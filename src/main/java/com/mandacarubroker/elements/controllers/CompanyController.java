package com.mandacarubroker.elements.controllers;

import com.mandacarubroker.elements.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.dtos.RequestStockDTO;
import com.mandacarubroker.elements.dtos.ResponseCompanyDTO;
import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.models.Stock;
import com.mandacarubroker.elements.services.CompanyService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

//@Controller
//public class CompanyController {
//
//    @Autowired
//    private CompanyService companyService;
//
//
//    @GetMapping("/elements/companies")
//    public String getAllPages(Model model){
//        return getOnePage(model, 1);
//    }
//
//    @GetMapping("/elements/companies/page/{pageNumber}")
//    public String  getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
//        Page<Company> page = companyService.findPage(currentPage);
//        int totalPages = page.getTotalPages();
//        long totalItems = page.getTotalElements();
//        List<Company> companies = page.getContent();
//
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalItems", totalItems);
//        model.addAttribute("companies", companies);
//
//        return "/elements/companies";
//    }
//
//    @GetMapping("/elements/companies/page/{pageNumber}/{field}")
//    public String getPageWithSort(Model model,
//                                  @PathVariable("pageNumber") int currentPage,
//                                  @PathVariable String field,
//                                  @PathParam("sortDir") String sortDir) {
//
//        Page<Company> page = companyService.findAllWithSort(field, sortDir, currentPage);
//        List<Company> companies = page.getContent();
//        int totalPages = page.getTotalPages();
//        long totalItems = page.getTotalElements();
//
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("totalItems", totalItems);
//
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//        model.addAttribute("companies", companies);
//        return "/elements/companies";
//    }
//
//    //The Get Country By Id
//    @GetMapping("/elements/company/{id}")
//    @ResponseBody
//    public Company getCountry(@PathVariable String id){
//        return companyService.getById(id);
//    }
//
//    @GetMapping("/elements/companyAdd")
//    public String addCompany(){
//        return "elements/companyAdd";
//    }
//
//    //The op parameter is either Edit or Details
//    @GetMapping("/elements/company/{op}/{id}")
//    public String editCompany(@PathVariable String id, @PathVariable String op, Model model){
//        Company company = companyService.getById(id);
//        model.addAttribute("company", company);
//        return "/elements/company"+ op;
//    }
//
//    @PostMapping("/elements/companies")
//    public String save(RequestCompanyDTO data){
//        companyService.validateAndCreateCompany(data);
//        return "redirect:/elements/companies";
//    }
//
//
//    @RequestMapping(value = "/elements/companies/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
//    public  String delete(@PathVariable String id){
//        companyService.delete(id);
//        return "redirect:/elements/companies";
//    }
//
//}



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?>  getCompanyById(@PathVariable String id) {
        return ResponseEntity.ok(
                Map.of(
                        "stocks", companyService.getById(id),
                        "companies", companyService.findAll(),
                        "stock", companyService.getById(id)
                )
        );
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
        //return "redirect:/elements/companies";
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        companyService.delete(id);
        return ResponseEntity.ok("Company deleted successfully");
       // return "redirect:/elements/companies";
    }
}


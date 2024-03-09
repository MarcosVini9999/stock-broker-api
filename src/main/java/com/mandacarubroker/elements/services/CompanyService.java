package com.mandacarubroker.elements.services;

import com.mandacarubroker.elements.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.dtos.RequestStockDTO;
import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.models.Stock;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();


    Page<Company> findPage(int pageNumber);
    Company getById(String id);


    void delete(String id);


    void update(Company company);

    List<Company> findByKeyword(String keyword);

    Page<Company> findAllWithSort(String field, String direction, int pageNumber);

    void save(Company company);


    void validateAndCreateCompany(RequestCompanyDTO data);

    void validateAndUpdateCompany(String id, RequestCompanyDTO data);


}

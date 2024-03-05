package com.mandacarubroker.elements.services;

import com.mandacarubroker.elements.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.dtos.ResponseCompanyDTO;
import com.mandacarubroker.elements.models.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {

    public List<ResponseCompanyDTO> findAll();


//    Page<Company> findPage(int pageNumber);
     ResponseCompanyDTO getById(String id);


    void delete(String id);


    void update(Company company);

//    List<Company> findByKeyword(String keyword);
//
//    Page<Company> findAllWithSort(String field, String direction, int pageNumber);

    public void validateAndUpdateCompany(String id, RequestCompanyDTO data);

    void save(Company company);


    void validateAndCreateCompany(RequestCompanyDTO data);

    //Stock validateAndUpdateCompany(String id, RequestStockDTO data );


}

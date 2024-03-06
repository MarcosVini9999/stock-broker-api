package com.mandacarubroker.elements.services;




import com.mandacarubroker.elements.domain.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.domain.dtos.ResponseCompanyDTO;
import com.mandacarubroker.elements.domain.entities.Company;

import java.util.List;

public interface CompanyService {

    public List<ResponseCompanyDTO> findAll();


    ResponseCompanyDTO getById(String id);


    void delete(String id);


    void update(Company company);


    public void validateAndUpdateCompany(String id, RequestCompanyDTO data);

    void save(Company company);


    void validateAndCreateCompany(RequestCompanyDTO data);




}

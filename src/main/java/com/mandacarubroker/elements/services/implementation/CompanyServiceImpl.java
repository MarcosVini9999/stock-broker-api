package com.mandacarubroker.elements.services.implementation;


import com.mandacarubroker.elements.domain.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.domain.dtos.ResponseCompanyDTO;
import com.mandacarubroker.elements.domain.entities.Company;
import com.mandacarubroker.elements.repositories.CompanyRepository;
import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<ResponseCompanyDTO> findAll(){

        return companyRepository.findAll().stream().map(ResponseCompanyDTO::new).toList();
    }



    public  void save(Company company){
        companyRepository.save(company);
    }

    public void delete(String id) {
        companyRepository.deleteById(id);
    }

    public ResponseCompanyDTO getById(String id) {
        return companyRepository.findById(id).map(ResponseCompanyDTO::new).orElse(null);
    }

    public void update(Company company) {
        companyRepository.save(company);
    }

    public List<Company> findByKeyword(String keyword){
        return companyRepository.findByKeyword(keyword);
    }

    public static void validateRequestCompanyDTO(RequestCompanyDTO data) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<RequestCompanyDTO>> violations = validator.validate(data);

            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder("Validation failed. Details: ");

                for (ConstraintViolation<RequestCompanyDTO> violation : violations) {
                    errorMessage.append(String.format("[%s: %s], ", violation.getPropertyPath(), violation.getMessage()));
                }

                errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

                throw new ConstraintViolationException(errorMessage.toString(), violations);
            }
        }catch (ValidationException ve) {
            throw new ValidationException(ve.getMessage());
        }

    }

    public void validateAndUpdateCompany(String id, RequestCompanyDTO data) {
        validateRequestCompanyDTO(data);
        Optional<Company> companyId = companyRepository.findById(id);

        companyId
                .map(company -> {
                    company.setCapital(data.capital());
                    company.setName(data.name());
                    company.setNationality(data.nationality());


                    return companyRepository.save(company);
                });
    }





    private void findByCnpj(RequestCompanyDTO data) {
        Optional<Company> company = companyRepository.findByCnpj(data.cnpj());
        if(company.isPresent()) {
            throw new DataIntegratyViolationException("Company already registered with this CNPJ");
        }
    }





    public void validateAndCreateCompany(RequestCompanyDTO data) {
        validateRequestCompanyDTO(data);
        findByCnpj(data);

        Company newCompany = new Company(data);
        companyRepository.save(newCompany);
    }

}

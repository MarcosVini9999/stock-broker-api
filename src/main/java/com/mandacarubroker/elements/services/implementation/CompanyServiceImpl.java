package com.mandacarubroker.elements.services.implementation;

import com.mandacarubroker.elements.dtos.RequestCompanyDTO;
import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.repositories.CompanyRepository;
import com.mandacarubroker.elements.services.CompanyService;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Page<Company> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 5);
        return companyRepository.findAll(pageable);
    }

    public  void save(Company company){
        companyRepository.save(company);
    }

    public void delete(String id) {
        companyRepository.deleteById(id);
    }

    public Company getById(String id) {
        return companyRepository.findById(id).orElse(null);
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




    public Page<Company> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

        return companyRepository.findAll(pageable);
    }
}

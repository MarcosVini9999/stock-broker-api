package com.mandacarubroker.elements.domain.dtos;



import com.mandacarubroker.elements.domain.entities.Company;
import com.mandacarubroker.elements.domain.entities.Stock;

import java.util.List;

public record ResponseCompanyDTO(String id, String cnpj, String name, float capital, String nationality , List<Stock> stocks, String Ticker) {

        public ResponseCompanyDTO(Company company){
            this(company.getId(),company.getCnpj(), company.getName(), company.getCapital(), company.getNationality(), company.getStocks(), company.getTicker());
        }
    }


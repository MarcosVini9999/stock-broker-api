package com.mandacarubroker.elements.dtos;

import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.models.Stock;

import java.util.List;

public record ResponseCompanyDTO(String id, String cnpj, String name, float capital, String nationality , List<Stock> stocks, String Ticker) {

        public ResponseCompanyDTO(Company company){
            this(company.getId(),company.getCnpj(), company.getName(), company.getCapital(), company.getNationality(), company.getStocks(), company.getTicker());
        }
    }


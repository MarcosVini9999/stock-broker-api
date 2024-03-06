package com.mandacarubroker.elements.domain.dtos;


import com.mandacarubroker.elements.domain.entities.Company;
import com.mandacarubroker.elements.domain.entities.Stock;

public record ResponseStockDTO(String id, String symbol, float price, String details, float variation, Company company) {

    public ResponseStockDTO(Stock stock){
        this(stock.getId(),stock.getSymbol(), stock.getPrice(), stock.getDetails(), stock.getVariation(), stock.getCompany());
    }
}


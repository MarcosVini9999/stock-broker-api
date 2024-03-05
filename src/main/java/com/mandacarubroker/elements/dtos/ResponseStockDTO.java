package com.mandacarubroker.elements.dtos;


import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.models.Stock;


public record ResponseStockDTO(String id, String symbol, float price, String details, float variation, Company company) {

    public ResponseStockDTO(Stock stock){
        this(stock.getId(),stock.getSymbol(), stock.getPrice(), stock.getDetails(), stock.getVariation(), stock.getCompany());
    }
}


package com.mandacarubroker.elements.services;


import com.mandacarubroker.elements.dtos.RequestStockDTO;
import com.mandacarubroker.elements.models.Company;
import com.mandacarubroker.elements.models.Stock;

import java.util.List;

public interface StockService {

    List<Stock> findAll();


    Stock findById(String id);

    void delete(String id);


    void validateAndCreateStock(RequestStockDTO data);

    Stock validateAndUpdateStock(String id, RequestStockDTO data );



}

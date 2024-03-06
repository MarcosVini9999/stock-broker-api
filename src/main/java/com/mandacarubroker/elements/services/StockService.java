package com.mandacarubroker.elements.services;

import com.mandacarubroker.elements.domain.dto.RequestStockDTO;
import com.mandacarubroker.elements.domain.stock.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStocks();

    Stock getStockById(String id);


    void deleteStock(String id);


    Stock validateAndCreateStock(RequestStockDTO data);

    Stock validateAndUpdateStock(String id, RequestStockDTO data );



}

package com.mandacarubroker.elements.services;

import com.mandacarubroker.elements.domain.dtos.RequestStockDTO;
import com.mandacarubroker.elements.domain.dtos.ResponseStockDTO;
import com.mandacarubroker.elements.domain.entities.Stock;

import java.util.List;

public interface StockService {

    List<ResponseStockDTO> findAll();



    ResponseStockDTO findById(String id);

    void delete(String id);


    void validateAndCreateStock(RequestStockDTO data);

    Stock validateAndUpdateStock(String id, RequestStockDTO data );



}

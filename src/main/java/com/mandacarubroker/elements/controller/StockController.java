package com.mandacarubroker.elements.controller;
import com.mandacarubroker.elements.domain.dtos.RequestStockDTO;
import com.mandacarubroker.elements.domain.dtos.ResponseStockDTO;
import com.mandacarubroker.elements.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/elements/stocks")
public class StockController {
    private final StockService stockService;
    @Autowired
    public StockController(StockService stockService ) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseStockDTO>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ResponseStockDTO>  getStockById(@PathVariable String id) {
        return ResponseEntity.ok(stockService.findById(id));
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editStock(@PathVariable String id, @RequestBody RequestStockDTO data) {
        stockService.validateAndUpdateStock(id, data);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Stock update successfully");
    }



    @PostMapping("/stockAdd")
    public ResponseEntity<String> addNew(@RequestBody RequestStockDTO data) {
        stockService.validateAndCreateStock(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Stock created successfully");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        stockService.delete(id);
        return ResponseEntity.ok("Stock deleted successfully");

    }
}
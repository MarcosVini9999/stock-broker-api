package com.mandacarubroker.elements.config;
import com.mandacarubroker.elements.domain.dto.RequestStockDTO;
import com.mandacarubroker.elements.domain.stock.Stock;
import com.mandacarubroker.elements.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private StockRepository repository;

    @Bean
    public void startDB() {
        final double VALOR_MAGICO_1 = 111D;
        final double VALOR_MAGICO_2 = 723.34;

        RequestStockDTO rdto = new RequestStockDTO("AABB2", "Petrobras", VALOR_MAGICO_1);
        RequestStockDTO rdto2 = new RequestStockDTO("BBAA5", "Apple", VALOR_MAGICO_2);
        Stock u1 = new Stock(rdto);
        Stock u2 = new Stock(rdto2);
        u1.setId("12345");
        u2.setId("5678");







        repository.saveAll(List.of(u1, u2));
    }
}

package com.mandacarubroker.controller.exceptions;

import com.mandacarubroker.elements.controller.exceptions.ControllerExceptionHandler;
import com.mandacarubroker.elements.controller.exceptions.StandardError;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import com.mandacarubroker.elements.services.exceptions.StockNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ControllerExceptionHandlerTest {

    private static final String STOCK_NOT_FOUND = "Stock not found";
    private static final String STOCK_ALREADY_REGISTERED = "Stock already registered with this symbol";
    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}
    @Test
    void whenStockNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .handleObjectNotFoundException(
                        new StockNotFoundException(STOCK_NOT_FOUND));
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(HttpStatus.NOT_FOUND, response.getBody().getStatus());
        assertEquals(LocalDateTime.class,response.getBody().getTimestamp().getClass());
    }
    @Test
    void whenDataIntegrityViolationExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolationException(
                        new DataIntegratyViolationException(STOCK_ALREADY_REGISTERED));
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(STOCK_ALREADY_REGISTERED, response.getBody().getError());
        assertEquals(HttpStatus.CONFLICT, response.getBody().getStatus());
    }
}
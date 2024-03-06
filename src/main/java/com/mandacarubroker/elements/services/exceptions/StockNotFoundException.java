package com.mandacarubroker.elements.services.exceptions;

public class StockNotFoundException extends RuntimeException{

    public StockNotFoundException() {
        super("Not found");
    }
    public StockNotFoundException(String message) {
        super(message);
    }
}

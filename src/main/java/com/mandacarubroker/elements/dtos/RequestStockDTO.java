package com.mandacarubroker.elements.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



public record RequestStockDTO(

        @NotNull(message = "CompanyId cannot be null")
        String companyid,
        @Pattern(regexp = "[A-Za-z]{4}\\d", message = "Symbol must be 4 letters followed by 1 number")
        String symbol,
        @NotNull(message = "Price cannot be null")
        float price,
        @NotNull(message = "Details cannot be null")
        String details


){
        }








































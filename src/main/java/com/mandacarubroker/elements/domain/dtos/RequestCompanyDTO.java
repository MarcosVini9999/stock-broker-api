package com.mandacarubroker.elements.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestCompanyDTO(

        @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "\n" +
                "The regular expression \"/^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$/\" validates a format that corresponds to a CNPJ (Cadastro Nacional da Pessoa Jurídica")
        String cnpj,

        @NotNull(message = "Capital cannot be null")
        Float capital,

        @NotNull(message = "Name cannot be null")
        String name,

        @NotNull(message = "Nationality cannot be null")
        String nationality,

        @Pattern(regexp = "[A-Za-z]{4}", message = "Symbol must be 4 letters")
        String ticker
) {






}

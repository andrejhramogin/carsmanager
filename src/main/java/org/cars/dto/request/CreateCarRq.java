package org.cars.dto.request;

import jakarta.validation.constraints.*;

public record CreateCarRq(

        @NotBlank(message = "Brand name must not be blank")
        @Size(min = 1, max = 50, message = "Brand name must be between 1 and 50 characters")
        String brand,

        @NotBlank(message = "Model name must not be blank")
        @Size (min = 1, max = 50, message = "Model name must be between 1 and 50 characters")
        String model,

        @NotNull(message = "year can`t be null")
        @Min(value = 1885, message = "Year must be greater than 1885")
        @Max(value = 2023, message = "Year must be less than 2023")
        Integer year,

        @NotNull(message = "price can`t be null")
        @Min(value = 1, message = "Price must be >= 1")
        Double price
) {
}
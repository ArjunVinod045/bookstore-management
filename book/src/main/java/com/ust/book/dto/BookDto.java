package com.ust.book.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record BookDto(

        long id,

        @NotNull(message = "Title cannot be empty")
        @Length(min = 1,max = 255)
        String title,

        @NotNull(message = "Author cannot be empty")
        String author,

        @NotNull(message = "Price  cannot be empty")
        @DecimalMin("0.1")
        @Digits(integer = Integer.MAX_VALUE,fraction = 2)
        double price,

        @NotNull(message = "Stock cannot be empty")
        @Min(value = 0,message = "Stock cannot be negative")
        long stock
) {
}

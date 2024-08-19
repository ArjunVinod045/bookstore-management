package com.ust.order.dto;

import com.ust.order.model.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderDto(
        @NotNull(message = "CustomerId cannot be empty")
        long customerId,

        @NotNull(message = "BookId cannot be empty")
        long bookId,

        @NotNull(message = "Quantity cannot be null")
        @Min(1)
        int quantity,

        @NotNull(message = "Status cannot be null")
        Status status
) {
}

//TODO Ensure that book id exist in book table before adding
//TODO Ensure customer exists before adding an order
//TODO Ensure that stock is > quantity at the time of ordering
//TODO Change ENUM Usage for better validation
package com.ust.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CustomerDto (
        long id,

        @NotNull(message = "Name cannot be null")
        String name,

        @NotNull(message = "Email cannot be null")
        @Email
        String email,

        @NotNull(message = "Phone no cannot be null")
        @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$", message = "Phone Number is invalid")
        String phoneNo
){
}

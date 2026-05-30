package com.payflash.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductRequestDto {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price cannot be negative")
    private Double price;

    private boolean available;
}
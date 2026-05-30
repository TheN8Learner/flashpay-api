package com.payflash.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequestDto {
    @PositiveOrZero(message = "Total Amount cant be Negative")
    private double totalAmount;
    private  Long id;
    @NotNull(message = "customerId is required")
    private Long customerId;


}

package com.payflash.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponseDto {

    private Long id;
    private double totalAmount;
    private boolean paid;
    private Long customerId;

}

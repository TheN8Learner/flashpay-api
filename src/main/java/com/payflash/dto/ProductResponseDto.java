package com.payflash.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private double price;
    private boolean available;
}

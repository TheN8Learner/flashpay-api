package com.payflash.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The name cant be empty")
    private String name;
    @PositiveOrZero(message = "The price cant be negative")
    private double price;
    private boolean available;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
        this.available = false;
    }

    public void isAvailable(boolean available){
        this.available = available;
    }
}

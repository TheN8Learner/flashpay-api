package com.payflash.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

    public Product() {}

    public Long getId(){return  id;}
    public  String getName(){return  name;}
    public void setName(String name){
        this.name = name;
    }
    public double getPrice(){return price;}
    public void setPrice(double price){
        this.price = price;
    }
    public boolean isAvailable(){return  available;}
    public void setAvailable(boolean available){
        this.available = available;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", Name=" + name + ", price=" + price + "}";
    }
}

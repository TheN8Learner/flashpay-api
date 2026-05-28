package com.payflash.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "totalAmount is required")
    @Min(value = 0, message = "Total Amount must be equal or greater than 0")
    private Double totalAmount;
    private boolean paid;

    public Order() {}

    public Order(double totalAmount){
        this.totalAmount = totalAmount;
        this.paid = false;
    }

    public Long getId(){ return id;}
    public double getTotalAmount(){ return totalAmount;}
    public  void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }
    public boolean isPaid(){ return  paid;}

    public void markAsPaid(){
        this.paid = true;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", totalAmount=" + totalAmount + ", paid=" + paid + "}";
    }
}


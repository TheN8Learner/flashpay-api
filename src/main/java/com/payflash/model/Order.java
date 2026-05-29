package com.payflash.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Order{
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "totalAmount is required")
    @Min(value = 0, message = "Total Amount must be equal or greater than 0")
    private Double totalAmount;
    private boolean paid;


    public Order(double totalAmount){
        this.totalAmount = totalAmount;
        this.paid = false;
    }

    public void markAsPaid() {
        this.paid = true;
    }
}


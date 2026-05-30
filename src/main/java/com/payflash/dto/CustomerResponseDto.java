package com.payflash.dto;

import com.payflash.model.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private  long id;
    private String firstName;
    private String lastName;
    private String email;
}

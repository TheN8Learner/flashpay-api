package com.payflash.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.print.attribute.standard.MediaSize;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CustomerRequestDto {
    @NotBlank(message = "The firstName cant be empty")
    private String firstName;
    @NotBlank(message = "The lastName cant be empty")
    private String lastName;
    @Email(message = "The email has to be a well-formed email address")
    private String email;
}

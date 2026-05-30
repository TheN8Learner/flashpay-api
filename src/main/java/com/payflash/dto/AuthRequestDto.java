package com.payflash.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRequestDto {

    @Email(message = "The email need to be a valid email format")
    private String email;
    private String password;
}

package com.moneyminder.moneyminderusers.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String username;
    @Email(message = "El usuario debe tener un email con el formato correcto")
    private String email;
    private String oldPassword;
    private String newPassword;

}

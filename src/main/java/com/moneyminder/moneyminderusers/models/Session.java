package com.moneyminder.moneyminderusers.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private String id;
    @NotBlank(message = "El token no debe ser nullo")
    private String token;
    private LocalDate creationDate;
    @NotNull(message = "El token debe tener una fecha de caducidad")
    private LocalDate expirationDate;
    @NotBlank(message = "El token debe estar asociado a un usuario")
    private String user;

}

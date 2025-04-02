package com.moneyminder.moneyminderusers.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotBlank(message = "El usuario debe tener un nombre de usuario")
    private String username;
    @Email(message = "El usuario debe tener un email con el formato correcto")
    private String email;
    @NotBlank(message = "El usuario debe tener tener una contraseña")
    private String password;
    private List<String> groups;
    private List<String> sessions;
    private List<String> requestingGroups;
    private List<String> requestedGroups;

}

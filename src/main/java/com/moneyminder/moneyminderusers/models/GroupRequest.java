package com.moneyminder.moneyminderusers.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {

    private String id;
    private String group;
    @NotBlank(message = "La solicitud debe estar asociada a un solicitante")
    private String requestingUser;
    @NotBlank(message = "La solicitud debe estar asociada a un destinatario")
    private String requestedUser;
    private LocalDate date;
    private Boolean accepted;

}

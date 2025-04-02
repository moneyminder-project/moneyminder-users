package com.moneyminder.moneyminderusers.models;

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
public class Group {

    private String id;
    @NotBlank(message = "El grupo debe tener un nombre")
    private String name;
    private List<String> users;
    private List<String> groupRequests;

}

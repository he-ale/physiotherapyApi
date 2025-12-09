package com.fldsmdfr.domainTherapies.dto.dtoController;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TherapyRegister {

    @NotNull
    @Size(min = 2, max = 100)
    @Pattern(
        regexp = "^[A-Za-z0-9 ]+$",
        message = "Only letters, numbers, and spaces are allowed"
    )
    private String name;

    @NotNull
    @Size(min = 5, max = 500)
    @Pattern(
        regexp = "^[A-Za-z0-9 ]+$",
        message = "Only letters, numbers, and spaces are allowed"
    )
    private String description;

    @NotNull
    private Integer durationMin;

    @NotNull
    private Double priceBase;

    @NotNull
    @Pattern(
        regexp = "^[A-Za-z0-9 ]+$",
        message = "Only letters, numbers, and spaces are allowed"
    )
    private String category;
}

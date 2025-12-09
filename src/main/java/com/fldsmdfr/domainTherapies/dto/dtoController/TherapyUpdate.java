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
@Setter
@Getter
public class TherapyUpdate{
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
    @NotNull
    @Pattern(
        regexp = "^(AVAILABLE|DELETED|NO_AVAILABLE)$",
        message = "Only 'AVAILABLE', 'DELETED', or 'NO_AVAILABLE' are allowed"
    )
    private String status;
}

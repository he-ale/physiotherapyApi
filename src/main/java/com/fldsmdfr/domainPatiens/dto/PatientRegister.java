package com.fldsmdfr.domainPatiens.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientRegister {
    
    @NotNull
    @Size(min = 20, max = 100)
    @Pattern(
        regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ -]+$",
        message = "Only Letters and spaces are allowed"
    )
    private String name;

    @NotNull
    @Pattern(
        regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$",
        message = "Email invalid"
    )
    private String email;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @Pattern(
        regexp = "^(?:\\+591|591)?[67]\\d{7}$",
        message = "Only phone numbers are allowed"
    )
    private String phone;

    @NotNull
    @Pattern(
        regexp="^(MALE|FEMALE|OTHER)$",
        message = "Only 'MALE', 'FEMALE' or 'OTHER' are allowed "
    )
    private String gender;

    @NotNull
    private String address;

    private List<String> medicalConditions;
}

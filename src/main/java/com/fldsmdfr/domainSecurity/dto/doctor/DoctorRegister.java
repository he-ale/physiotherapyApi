package com.fldsmdfr.domainSecurity.dto.doctor;

import com.fldsmdfr.domainSecurity.dto.user.UserAppRegister;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DoctorRegister extends UserAppRegister {
    
    @NotNull
    private String specialty;

    @NotNull
    private String licenseNumber;

    @Min(1)
    private Integer experience;

    @NotNull
    private String description;

    public DoctorRegister(String name, String email, String specialty, String licenseNumber, Integer experience,
            String description, String password, String phone) {
        super(name, email, password, phone);
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
        this.experience = experience;
        this.description = description;
    }
}

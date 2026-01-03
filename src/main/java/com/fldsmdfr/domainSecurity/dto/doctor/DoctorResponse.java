package com.fldsmdfr.domainSecurity.dto.doctor;

import java.time.LocalDate;

import com.fldsmdfr.domainSecurity.dto.user.UserAppResponse;
import com.fldsmdfr.domainSecurity.models.utilities.UserStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DoctorResponse extends UserAppResponse {
    
    private Long doctorId;

    private String specialty;

    private String licenseNumber;

    private Integer experience;

    private String description;

    public DoctorResponse(Long id, String name, String email, LocalDate createdAt, LocalDate updatedAt,
            UserStatus userStatus, Long id2, String specialty, String licenseNumber, Integer experience,
            String description) {
        super(id, name, email, createdAt, updatedAt, userStatus);
        doctorId = id2;
        this.specialty = specialty;
        this.licenseNumber = licenseNumber;
        this.experience = experience;
        this.description = description;
    }
}

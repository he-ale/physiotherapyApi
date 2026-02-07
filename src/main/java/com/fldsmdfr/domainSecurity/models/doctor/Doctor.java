package com.fldsmdfr.domainSecurity.models.doctor;

import com.fldsmdfr.domainSecurity.models.user.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor extends AppUser{

    private String specialty;

    @Column(unique = true)
    private String licenseNumber;

    private Integer experience;

    private String description;
}

package com.fldsmdfr.domainSecurity.models.doctor;

import com.fldsmdfr.domainSecurity.models.user.UserApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor extends UserApp{

    private String specialty;

    @Column(unique = true)
    private String licenseNumber;

    private Integer experience;

    private String description;
}

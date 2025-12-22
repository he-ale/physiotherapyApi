package com.fldsmdfr.domainPatiens.dto;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fldsmdfr.domainPatiens.models.utilities.Gender;
import com.fldsmdfr.domainPatiens.models.utilities.StatusPatient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PatientResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private String phone;
    private Gender gender;
    private String address;
    private ArrayList<String> medicalConditions;
    private StatusPatient status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}

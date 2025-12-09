package com.fldsmdfr.domainPatiens.models;

import java.time.LocalDate;

import com.fldsmdfr.domainPatiens.models.utilities.Gender;
import com.fldsmdfr.domainPatiens.models.utilities.StatusPatient;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String email;
    
    private LocalDate birthDate;
    
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    private String address;
    
    private String medicalConditions;

    @Enumerated(EnumType.STRING)
    private StatusPatient status;

    private LocalDate createdAt;
    
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt= LocalDate.now();
        status= StatusPatient.PATIENT;
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = LocalDate.now();
    }

    public String toString(){
        return "Patient{id="+id+", name="+name+", email="+email+", birthDate="+birthDate+", phone="+phone+", gender=" + gender.toString() + ", address="+address+", medicalConditions="+medicalConditions+", createdAt="+createdAt+", updatedAt="+updatedAt+"}";
    }
}

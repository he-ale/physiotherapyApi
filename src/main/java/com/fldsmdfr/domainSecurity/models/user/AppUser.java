package com.fldsmdfr.domainSecurity.models.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fldsmdfr.domainSecurity.models.role.AppRole;
import com.fldsmdfr.domainSecurity.models.utilities.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private LocalDate createdAt;
    
    private LocalDate updatedAt;
    
    private LocalDateTime sortDate;
    
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(unique = true)
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "app_user_role",
        joinColumns = @JoinColumn(name= "app_user_id"),
        inverseJoinColumns = @JoinColumn(name= "role_id")
    )
    private Set<AppRole> roles;

    @PrePersist
    public void prePersist(){
        createdAt= LocalDate.now();
        sortDate= LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt= LocalDate.now();
        sortDate= LocalDateTime.now();
    }
}

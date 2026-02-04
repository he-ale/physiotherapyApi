package com.fldsmdfr.domainSecurity.models.secretary;

import com.fldsmdfr.domainSecurity.models.user.UserApp;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Secretary extends UserApp {

    private Shift shift;
}

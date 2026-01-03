package com.fldsmdfr.domainSecurity.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAppUpdate {

    @NotNull
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
}

package com.fldsmdfr.domainSecurity.dto.role;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleAppUpdate {
    @Size(min = 3, max = 20)
    @Pattern(
        regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ -]+$",
        message = "Only Letters and spaces are allowed"
    )
    private String name;
}

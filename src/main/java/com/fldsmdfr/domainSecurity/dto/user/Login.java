package com.fldsmdfr.domainSecurity.dto.user;

import jakarta.validation.constraints.NotNull;

public record Login(
    @NotNull
    String username,
    @NotNull
    String password
) {
}

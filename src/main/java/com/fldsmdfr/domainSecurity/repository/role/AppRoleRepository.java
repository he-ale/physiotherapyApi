package com.fldsmdfr.domainSecurity.repository.role;

import com.fldsmdfr.common.repository.GenericRepository;
import com.fldsmdfr.domainSecurity.models.role.AppRole;
import java.util.Optional;


public interface AppRoleRepository extends GenericRepository<AppRole, Long> {

    Optional<AppRole> findByName(String name);    
}

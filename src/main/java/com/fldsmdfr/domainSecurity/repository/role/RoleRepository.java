package com.fldsmdfr.domainSecurity.repository.role;

import com.fldsmdfr.common.repository.GenericRepository;
import com.fldsmdfr.domainSecurity.models.role.RoleApp;
import java.util.Optional;


public interface RoleRepository extends GenericRepository<RoleApp, Long> {

    Optional<RoleApp> findByName(String name);    
}

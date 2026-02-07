package com.fldsmdfr.domainSecurity.repository.user;

import com.fldsmdfr.common.repository.GenericRepository;
import com.fldsmdfr.domainSecurity.models.user.AppUser;


public interface UserAppRepository extends GenericRepository<AppUser, Long> {
    
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    boolean existsByPhoneAndIdNot(String phone, Long id);
}

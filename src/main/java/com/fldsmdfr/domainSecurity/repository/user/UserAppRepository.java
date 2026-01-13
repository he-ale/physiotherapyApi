package com.fldsmdfr.domainSecurity.repository.user;

import com.fldsmdfr.common.repository.GenericRepository;
import com.fldsmdfr.domainSecurity.models.user.UserApp;


public interface UserAppRepository extends GenericRepository<UserApp, Long> {
    
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    boolean exexistsByPhoneAndIdNot(String phone, Long id);
}

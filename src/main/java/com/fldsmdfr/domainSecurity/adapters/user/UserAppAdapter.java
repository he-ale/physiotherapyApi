package com.fldsmdfr.domainSecurity.adapters.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fldsmdfr.common.dto.PageResponse;
import com.fldsmdfr.domainSecurity.dto.user.UserAppResponse;
import com.fldsmdfr.domainSecurity.models.user.AppUser;

@Component
public class UserAppAdapter {
    
    private final ModelMapper modelMapper;

    public UserAppAdapter(ModelMapper modelMapper){
        this.modelMapper= modelMapper;

        this.modelMapper.getConfiguration().setSkipNullEnabled(true);

        this.modelMapper.typeMap(AppUser.class, UserAppResponse.class)
            .addMappings(mapper->{
                mapper.map(AppUser::getId, UserAppResponse::setId);
                mapper.map(AppUser::getCreatedAt, UserAppResponse::setCreatedAt);
                mapper.map(AppUser::getEmail, UserAppResponse::setEmail);
                mapper.map(AppUser::getName, UserAppResponse::setName);
                mapper.map(AppUser::getPhone, UserAppResponse::setUpdatedAt);
                mapper.map(AppUser::getUserStatus, UserAppResponse::setUserStatus);
            }
        );
    }

    public UserAppResponse userAppResponseToUserAppResponse(AppUser appUser){
        return modelMapper.map(modelMapper, UserAppResponse.class);
    }

}

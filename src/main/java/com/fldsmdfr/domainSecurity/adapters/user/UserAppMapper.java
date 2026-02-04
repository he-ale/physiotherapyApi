package com.fldsmdfr.domainSecurity.adapters.user;

import org.modelmapper.ModelMapper;

import com.fldsmdfr.domainSecurity.dto.user.UserAppResponse;
import com.fldsmdfr.domainSecurity.models.user.UserApp;

public class UserAppMapper {
    
    private final ModelMapper modelMapper;

    public UserAppMapper(ModelMapper modelMapper){
        this.modelMapper= modelMapper;

        this.modelMapper.getConfiguration().setSkipNullEnabled(true);

        this.modelMapper.typeMap(UserApp.class, UserAppResponse.class)
            .addMappings(mapper->{
                mapper.map(UserApp::getId, UserAppResponse::setId);
                mapper.map(UserApp::getCreatedAt, UserAppResponse::setCreatedAt);
                mapper.map(UserApp::getEmail, UserAppResponse::setEmail);
                mapper.map(UserApp::getName, UserAppResponse::setName);
                mapper.map(UserApp::getPhone, UserAppResponse::setUpdatedAt);
                mapper.map(UserApp::getUserStatus, UserAppResponse::setUserStatus);
            }
        );
    }

    public UserAppResponse userAppResponseToUserAppResponse(UserApp userApp){
        return modelMapper.map(modelMapper, UserAppResponse.class);
    }
}

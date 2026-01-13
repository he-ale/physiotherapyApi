package com.fldsmdfr.domainSecurity.adapters.role;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fldsmdfr.domainSecurity.dto.role.RoleAppCreate;
import com.fldsmdfr.domainSecurity.dto.role.RoleAppResponse;
import com.fldsmdfr.domainSecurity.dto.role.RoleAppUpdate;
import com.fldsmdfr.domainSecurity.models.role.RoleApp;


@Component
public class RoleAppMapper {
    
    private final ModelMapper modelMapper;

    public RoleAppMapper(ModelMapper modelMapper){
        this.modelMapper= modelMapper;

        this.modelMapper.getConfiguration().setSkipNullEnabled(true);

        this.modelMapper.typeMap(RoleApp.class, RoleAppResponse.class)
        .addMappings(mapper->{
            mapper.map(RoleApp::getId, RoleAppResponse::setId);
            mapper.map(RoleApp::getName, RoleAppResponse::setName);
        });

        this.modelMapper.typeMap(RoleAppCreate.class, RoleApp.class)
        .addMappings(mapper->{
            mapper.map(RoleAppCreate::getName, RoleApp::setName);
        });

        this.modelMapper.typeMap(RoleAppUpdate.class, RoleApp.class)
        .addMappings(mapper->{
            mapper.map(RoleAppUpdate::getName, RoleApp::setName);
        });
    }

    public RoleApp roleAppCreateToRoleApp(RoleAppCreate roleAppCreate){
        return modelMapper.map(roleAppCreate, RoleApp.class);
    }

    public RoleApp roleAppUpdateToRoleApp(RoleAppUpdate roleAppUpdate){
        return modelMapper.map(roleAppUpdate, RoleApp.class);
    }

    public RoleAppResponse roleAppToRoleAppResponse(RoleApp roleApp){
        return modelMapper.map(roleApp, RoleAppResponse.class);
    }
}

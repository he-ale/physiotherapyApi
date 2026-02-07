package com.fldsmdfr.domainSecurity.adapters.role;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fldsmdfr.domainSecurity.dto.role.AppRoleCreate;
import com.fldsmdfr.domainSecurity.dto.role.AppRoleResponse;
import com.fldsmdfr.domainSecurity.dto.role.AppRoleUpdate;
import com.fldsmdfr.domainSecurity.models.role.AppRole;


@Component
public class AppRoleAdapter {
    
    private final ModelMapper modelMapper;

    public AppRoleAdapter(ModelMapper modelMapper){
        this.modelMapper= modelMapper;

        this.modelMapper.getConfiguration().setSkipNullEnabled(true);

        this.modelMapper.typeMap(AppRole.class, AppRoleResponse.class)
        .addMappings(mapper->{
            mapper.map(AppRole::getId, AppRoleResponse::setId);
            mapper.map(AppRole::getName, AppRoleResponse::setName);
        });

        this.modelMapper.typeMap(AppRoleCreate.class, AppRole.class)
        .addMappings(mapper->{
            mapper.map(AppRoleCreate::getName, AppRole::setName);
        });

        this.modelMapper.typeMap(AppRoleUpdate.class, AppRole.class)
        .addMappings(mapper->{
            mapper.map(AppRoleUpdate::getName, AppRole::setName);
        });
    }

    public AppRole appRoleCreateToAppRole(AppRoleCreate appRoleCreate){
        return modelMapper.map(appRoleCreate, AppRole.class);
    }

    public AppRole appRoleUpdateToAppRole(AppRoleUpdate appRoleUpdate){
        return modelMapper.map(appRoleUpdate, AppRole.class);
    }

    public AppRoleResponse appRoleToRoleAppResponse(AppRole appRole){
        return modelMapper.map(appRole, AppRoleResponse.class);
    }

    public List<AppRoleResponse> appRolesToAppRolesResponse(List<AppRole> roles){
        return roles.stream().map(this::appRoleToRoleAppResponse).toList();
    }
}

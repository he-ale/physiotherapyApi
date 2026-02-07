package com.fldsmdfr.domainSecurity.controllers.role;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fldsmdfr.domainSecurity.adapters.role.AppRoleAdapter;
import com.fldsmdfr.domainSecurity.dto.role.AppRoleCreate;
import com.fldsmdfr.domainSecurity.dto.role.AppRoleResponse;
import com.fldsmdfr.domainSecurity.dto.role.AppRoleUpdate;
import com.fldsmdfr.domainSecurity.models.role.AppRole;
import com.fldsmdfr.domainSecurity.services.role.AppRoleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/api/v1/appRole")
@Tag(name = "AppRole")
@AllArgsConstructor
public class RoleAppController {
    
    private final AppRoleService appRoleService;

    private final AppRoleAdapter appRoleAdapter;

    @PostMapping("/createAppRole")
    public ResponseEntity<AppRoleResponse> createAppRole(@RequestBody AppRoleCreate body) {
        AppRole appRole= appRoleAdapter.appRoleCreateToAppRole(body);
        appRole= appRoleService.createRole(appRole);
        AppRoleResponse response= appRoleAdapter.appRoleToRoleAppResponse(appRole);
        URI created = URI.create("/api/v1/appRole/" + response.getId());
        return ResponseEntity.created(created).body(response);
    }
    
    @PutMapping("/updateAppRole/{name}")
    public ResponseEntity<AppRoleResponse> updateAppRole(@PathVariable String name, @RequestBody AppRoleUpdate entity) {
        AppRole appRoleToUpdate= appRoleAdapter.appRoleUpdateToAppRole(entity);
        AppRole roleAppUpdated= appRoleService.updateAppRole(name.toUpperCase(), appRoleToUpdate);
        AppRoleResponse response= appRoleAdapter.appRoleToRoleAppResponse(roleAppUpdated);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/appRoles")
    public ResponseEntity<List<AppRoleResponse>> appRoles() {
        List<AppRoleResponse> reponse= appRoleAdapter.appRolesToAppRolesResponse(appRoleService.findAll());
        return ResponseEntity.ok().body(reponse);
    }

    @GetMapping("/appRoleByName/{name}")
    public ResponseEntity<AppRoleResponse> getAppRoleByName(@PathVariable String name) {
        AppRole roleApp= appRoleService.findRoleByName(name.toUpperCase());
        AppRoleResponse response= appRoleAdapter.appRoleToRoleAppResponse(roleApp);
        return ResponseEntity.ok().body(response);
    }
    
    @DeleteMapping("/deleteAppRole/{id}")
    public ResponseEntity<Void> deleteAppRole(@PathVariable Long id){
        appRoleService.removeAppRole(id);
        return ResponseEntity.noContent().build();
    }
}

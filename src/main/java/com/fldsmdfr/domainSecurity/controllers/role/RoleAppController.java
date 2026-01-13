package com.fldsmdfr.domainSecurity.controllers.role;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fldsmdfr.domainSecurity.adapters.role.RoleAppMapper;
import com.fldsmdfr.domainSecurity.dto.role.RoleAppCreate;
import com.fldsmdfr.domainSecurity.dto.role.RoleAppResponse;
import com.fldsmdfr.domainSecurity.dto.role.RoleAppUpdate;
import com.fldsmdfr.domainSecurity.models.role.RoleApp;
import com.fldsmdfr.domainSecurity.services.role.RoleAppService;

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
@RequestMapping(path = "/api/roleApp")
@Tag(name = "RoleApp")
@AllArgsConstructor
public class RoleAppController {
    
    private final RoleAppService roleAppService;

    private final RoleAppMapper roleAppMapper;

    @PostMapping("/create")
    public ResponseEntity<RoleAppResponse> createRoleApp(@RequestBody RoleAppCreate body) {
        RoleApp roleApp= roleAppMapper.roleAppCreateToRoleApp(body);
        RoleApp newRoleApp= roleAppService.createRole(roleApp);
        RoleAppResponse response= roleAppMapper.roleAppToRoleAppResponse(newRoleApp);
        URI created = URI.create("/api/roleApp/" + response.getId());
        return ResponseEntity.created(created).body(response);
    }
    
    @PutMapping("/update/{name}")
    public ResponseEntity<RoleAppResponse> updateRoleApp(@PathVariable String name, @RequestBody RoleAppUpdate entity) {
        RoleApp roleAppToUpdate= roleAppMapper.roleAppUpdateToRoleApp(entity);
        RoleApp roleAppUpdated= roleAppService.updateRole(name.toUpperCase(), roleAppToUpdate);
        RoleAppResponse response= roleAppMapper.roleAppToRoleAppResponse(roleAppUpdated);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleAppResponse>> getMethodName() {
        List<RoleAppResponse> reponse= roleAppService.findAllRoleApp().stream().map(r->roleAppMapper.roleAppToRoleAppResponse(r)).toList();
        return ResponseEntity.ok().body(reponse);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleAppResponse> getRoleAppByName(@PathVariable String name) {
        RoleApp roleApp= roleAppService.findRoleApp(name.toUpperCase());
        RoleAppResponse response= roleAppMapper.roleAppToRoleAppResponse(roleApp);
        return ResponseEntity.ok().body(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){
        roleAppService.removeRoleApp(id);
        return ResponseEntity.noContent().build();
    }
}

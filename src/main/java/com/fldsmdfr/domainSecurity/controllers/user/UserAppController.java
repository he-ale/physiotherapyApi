package com.fldsmdfr.domainSecurity.controllers.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fldsmdfr.domainSecurity.adapters.user.UserAppMapper;
import com.fldsmdfr.domainSecurity.dto.user.UserAppResponse;
import com.fldsmdfr.domainSecurity.models.user.UserApp;
import com.fldsmdfr.domainSecurity.services.user.UserAppService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api")
@Tag(name = "UserApp")
@AllArgsConstructor
public class UserAppController {

    private final UserAppService userAppService;
    private final UserAppMapper userAppMapper;

    @GetMapping("/userApp/{id}")
    public ResponseEntity<UserAppResponse> getDoctor(@PathVariable Long id) {
        UserApp userApp= userAppService.findById(id);
        UserAppResponse response= userAppMapper.userAppResponseToUserAppResponse(userApp);
        return ResponseEntity.ok().body(response);
    }     
}

package com.projects.ecommerce.controller;


import com.projects.ecommerce.entity.Role;
import com.projects.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping("/createNewRole")
    private Role createNewRole(@RequestBody Role role){
       return  roleService.createRole(role);
    }
}

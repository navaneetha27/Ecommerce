package com.projects.ecommerce.controller;


import com.projects.ecommerce.entity.JwtRequest;
import com.projects.ecommerce.entity.JwtResponse;
import com.projects.ecommerce.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse CreateJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }

    @GetMapping("/hello")
    public  String getHello(){
        return "hellow";
    }
}

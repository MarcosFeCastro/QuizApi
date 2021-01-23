package com.api.quiz.controllers;

import com.api.quiz.security.JWTUtil;
import com.api.quiz.security.UserSS;
import com.api.quiz.dto.EmailDTO;
import com.api.quiz.services.AuthService;
import com.api.quiz.services.UserService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/auth")
public class AuthController {
    
    @Autowired
    private JWTUtil jwtUtil;
    
    @Autowired
    private AuthService service;
    
    @RequestMapping(value="/refresh_token", method=RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value="/forgot", method=RequestMethod.POST)
    public ResponseEntity<Void> newPassword(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
    
}
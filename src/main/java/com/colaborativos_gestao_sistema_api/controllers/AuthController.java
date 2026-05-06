package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.DTOs.AuthenticationDTO;
import com.colaborativos_gestao_sistema_api.DTOs.LoginResponseDTO;
import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.services.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse response) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserDetails) auth.getPrincipal());
        var user = (Employee) auth.getPrincipal();
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(
                user.getNome(),
                user.getCargo().toString()
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponseDTO> getMe(HttpServletRequest request) {
        var user = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(new LoginResponseDTO(
                user.getNome(),
                user.getCargo().toString()
        ));
    }
}
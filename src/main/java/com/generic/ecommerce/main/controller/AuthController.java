package com.generic.ecommerce.main.controller;

import com.generic.ecommerce.main.dto.request.RegisterRequestDTO;
import com.generic.ecommerce.main.dto.response.RegisterResponseDTO;
import com.generic.ecommerce.main.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthController {

    private final IAuthService userService;

    public AuthController(IAuthService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request) {
        RegisterResponseDTO response = userService.register(request);
        if (response.getErrors() == null || response.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}

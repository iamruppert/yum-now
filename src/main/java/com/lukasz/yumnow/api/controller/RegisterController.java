package com.lukasz.yumnow.api.controller;

import com.lukasz.yumnow.api.dto.RegisterRequestDto;
import com.lukasz.yumnow.api.dto.mapper.RegisterRequestDtoMapper;
import com.lukasz.yumnow.buisness.RegisterService;
import com.lukasz.yumnow.domain.RegisterRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final RegisterRequestDtoMapper registerRequestDtoMapper;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        try {
            RegisterRequest request = registerRequestDtoMapper.map(registerRequest);
            registerService.register(request);
            return ResponseEntity.ok("Successfully registered account");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

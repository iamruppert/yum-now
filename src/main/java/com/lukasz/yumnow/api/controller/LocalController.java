package com.lukasz.yumnow.api.controller;

import com.lukasz.yumnow.api.dto.LocalRequestDto;
import com.lukasz.yumnow.buisness.LocalService;
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
public class LocalController {

    private LocalService localService;

    @PostMapping("/owner/local/create")
    public ResponseEntity<?> createLocal(
            @RequestBody @Valid LocalRequestDto localRequestDto
    ) {

        try {
            localService.create(localRequestDto.getEmail(),localRequestDto.getLocal());
            return ResponseEntity.ok().body("Local created successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.plate.silverplate.userPhysical.controller;

import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.userPhysical.domain.dto.UserPhysicalRequest;
import com.plate.silverplate.userPhysical.service.UserPhysicalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserPhysicalController {
    private final UserPhysicalService userPhysicalService;

    @PostMapping("/userPhysicals")
    public ResponseEntity<String> createUserPhysical(@AuthenticationPrincipal User user,
                                                     @Valid @RequestBody UserPhysicalRequest physicalRequest) {
        Long createdId = userPhysicalService.createUserPhysical(physicalRequest, user);
        return ResponseEntity.ok("Created User Physical Id: " + createdId);
    }
}

package com.plate.silverplate.userPhysical.controller;

import com.plate.silverplate.user.domain.entity.User;
import com.plate.silverplate.userPhysical.domain.dto.request.UserPhysicalRequest;
import com.plate.silverplate.userPhysical.domain.dto.response.UserPhysicalCreateResponse;
import com.plate.silverplate.userPhysical.domain.dto.response.UserPhysicalResponse;
import com.plate.silverplate.userPhysical.domain.entity.UserPhysical;
import com.plate.silverplate.userPhysical.service.UserPhysicalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/userPhysicals")
@RequiredArgsConstructor
@RestController
public class UserPhysicalController {
    private final UserPhysicalService userPhysicalService;

    @PostMapping("")
    public ResponseEntity<UserPhysicalCreateResponse> createUserPhysical(@AuthenticationPrincipal User user,
                                                     @Valid @RequestBody UserPhysicalRequest physicalRequest) {
        UserPhysical userPhysical = userPhysicalService.createUserPhysical(physicalRequest, user);
        return ResponseEntity.ok(UserPhysicalCreateResponse.of(userPhysical));
    }

    @GetMapping("")
    public ResponseEntity<UserPhysicalResponse> getUserPhysical(@AuthenticationPrincipal User user) {
        UserPhysical userPhysical = userPhysicalService.getUserPhysical(user);

        return ResponseEntity.ok(UserPhysicalResponse.of(userPhysical));
    }

    @PatchMapping("")
    public ResponseEntity<UserPhysicalResponse> updateUserPhysical(@AuthenticationPrincipal User user,
                                                                   @Valid @RequestBody UserPhysicalRequest physicalRequest) {
        UserPhysical userPhysical = userPhysicalService.updateUserPhysical(physicalRequest, user);

        return ResponseEntity.ok(UserPhysicalResponse.of(userPhysical));
    }
}

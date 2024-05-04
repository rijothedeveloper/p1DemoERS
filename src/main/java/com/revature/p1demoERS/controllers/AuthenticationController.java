package com.revature.p1demoERS.controllers;

import com.revature.p1demoERS.dto.SignInResponseDto;
import com.revature.p1demoERS.dto.SigninRequestDto;
import com.revature.p1demoERS.dto.SignupRequestDto;
import com.revature.p1demoERS.dto.UserResponseDto;
import com.revature.p1demoERS.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequest) {
        UserResponseDto signupResponseDto = authenticationService.signup(signupRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signupResponseDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDto> signin(@Valid @RequestBody SigninRequestDto signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
}

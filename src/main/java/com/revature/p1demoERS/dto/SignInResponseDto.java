package com.revature.p1demoERS.dto;

import com.revature.p1demoERS.model.Role;

import java.util.Map;

public record SignInResponseDto(String token, String refreshToken, Role role) {
}

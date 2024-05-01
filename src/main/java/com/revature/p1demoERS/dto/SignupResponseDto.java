package com.revature.p1demoERS.dto;

import com.revature.p1demoERS.model.Role;

public record SignupResponseDto(String firstName, String lastName, String username, String email, Role role) {
}
package com.revature.p1demoERS.dto;

import com.revature.p1demoERS.model.Role;

public record UserResponseDto (String firstName, String lastName, String username, String email, Role role) {
}
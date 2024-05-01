package com.revature.p1demoERS.dto;

import com.revature.p1demoERS.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record SignupRequestDto(@NotBlank String firstName,
                               @NotBlank String lastName,
                               @NotBlank String username,
                               @NotBlank String email,
                               @Size(min=4, max=30)
                             String password,
                               Role role) {
}
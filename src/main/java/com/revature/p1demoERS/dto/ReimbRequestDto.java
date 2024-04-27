package com.revature.p1demoERS.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;

public record ReimbRequestDto ( String description, Double amount){

}

package com.revature.p1demoERS.dto;

import jakarta.validation.constraints.*;

public record ReimbRequestDto ( @NotBlank String description, @Min(1) Double amount){

}

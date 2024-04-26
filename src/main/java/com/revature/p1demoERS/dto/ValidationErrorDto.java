package com.revature.p1demoERS.dto;

import java.util.Map;

public record ValidationErrorDto (Map<String, String> errors) {
}

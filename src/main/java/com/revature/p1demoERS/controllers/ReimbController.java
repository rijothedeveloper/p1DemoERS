package com.revature.p1demoERS.controllers;


import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.dto.ReimbResponseDto;
import com.revature.p1demoERS.dto.ValidationErrorDto;
import com.revature.p1demoERS.services.ReimbService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping ("/api/v1/reimbursements")
public class ReimbController {


    ReimbService reimbService;

    @Autowired
    public ReimbController(ReimbService reimbService) {
        this.reimbService = reimbService;
    }

    @PostMapping
    public ResponseEntity<ReimbResponseDto> createReimb(@Valid @RequestBody ReimbRequestDto reimbRequestDto){

        ReimbResponseDto reimbResponseDto = reimbService.addReimb(reimbRequestDto);

        return ResponseEntity.status(201).body(reimbResponseDto);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handleValidateExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ValidationErrorDto validationErrorDto = new ValidationErrorDto(errors);

        return ResponseEntity.status(401).body(validationErrorDto);
    }


}

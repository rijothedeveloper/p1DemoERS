package com.revature.p1demoERS.services.impl;

import com.revature.p1demoERS.dao.ReimbDao;
import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.dto.ReimbResponseDto;
import com.revature.p1demoERS.dto.ValidationErrorDto;
import com.revature.p1demoERS.model.Reimbursement;
import com.revature.p1demoERS.model.Status;
import com.revature.p1demoERS.services.ReimbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@Service
public class ReimbserviceImpl implements ReimbService {


    @Autowired
    private  ReimbDao reimbDao;

    public ReimbserviceImpl(ReimbDao reimbDao) {
        this.reimbDao = reimbDao;
    }

    @Override
    public ReimbResponseDto addReimb(ReimbRequestDto reimbRequestDto) {


        Reimbursement reimb = new Reimbursement();

        reimb.setDescription(reimbRequestDto.description());

        reimb.setAmount(reimbRequestDto.amount());

        reimb.setStatus(Status.PENDING);

        reimbDao.save(reimb);

        return new ReimbResponseDto(reimb.getDescription(),reimb.getAmount(),reimb.getStatus());
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

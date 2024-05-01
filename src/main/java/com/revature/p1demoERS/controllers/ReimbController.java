package com.revature.p1demoERS.controllers;


import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.dto.ReimbResponseDto;
import com.revature.p1demoERS.model.Status;
import com.revature.p1demoERS.services.ReimbService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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

    @PreAuthorize("hasAuthority('USER') or hasAuthority('MANAGER')")
    @GetMapping
    public ReimbResponseDto geyMyReimbursements() {
        return new ReimbResponseDto("description", 10.0, Status.PENDING);
    }


}

package com.revature.p1demoERS.controllers;


import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.model.Reimbursement;
import com.revature.p1demoERS.model.Status;
import com.revature.p1demoERS.services.ReimbService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/api/v1/reimbursements")
public class ReimbController {


    ReimbService reimbService;

    @Autowired
    public ReimbController(ReimbService reimbService) {
        this.reimbService = reimbService;
    }

//    @PreAuthorize("hasAuthority('USER') or hasAuthority('MANAGER')")
    @PostMapping
    public ResponseEntity<Reimbursement> createReimb(@Valid @RequestBody ReimbRequestDto reimbRequestDto){

        Reimbursement reimbResponseDto = reimbService.addReimb(reimbRequestDto);

        return ResponseEntity.status(201).body(reimbResponseDto);

    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('MANAGER')")
    @GetMapping
    public ResponseEntity<List<Reimbursement>> geyMyReimbursements() {
        List<Reimbursement> reimb = reimbService.getMyReimbursements();
        return ResponseEntity.status(200).body(reimb);
    }


}

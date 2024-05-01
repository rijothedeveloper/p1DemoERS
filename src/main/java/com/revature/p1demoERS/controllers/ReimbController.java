package com.revature.p1demoERS.controllers;


import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.dto.UpdateReimbRequestDto;
import com.revature.p1demoERS.dto.UpdateStatusRequestDto;
import com.revature.p1demoERS.model.Reimbursement;
import com.revature.p1demoERS.model.Status;
import com.revature.p1demoERS.services.ReimbService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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

    @PreAuthorize("hasAuthority('USER') or hasAuthority('MANAGER')")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Reimbursement>> getStatusFilteredReimbursements(@PathVariable Status status) {
        List<Reimbursement> reimb = reimbService.getStatusFilteredReimbursements(status);
        return ResponseEntity.status(200).body(reimb);
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('MANAGER')")
    @PatchMapping("/updateDescription")
    public ResponseEntity<Reimbursement> updateDescription(@RequestBody UpdateReimbRequestDto updateReimbRequestDto) {
        Reimbursement reimb = reimbService.updateDescription(updateReimbRequestDto.id(), updateReimbRequestDto.description());
        return ResponseEntity.status(200).body(reimb);
    }

    /** Manager stories */

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/allReimbursements")
    public ResponseEntity<List<Reimbursement>> geyAllReimbursements() {
        List<Reimbursement> reimb = reimbService.getAllReimbursements();
        return ResponseEntity.status(200).body(reimb);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/allReimbursements/{status}")
    public ResponseEntity<List<Reimbursement>> geyAllReimbursements(@PathVariable Status status) {
        List<Reimbursement> reimb = reimbService.getAllReimbursements(status);
        return ResponseEntity.status(200).body(reimb);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PatchMapping("/updateStatus")
    public ResponseEntity<Reimbursement> updateStatus(@RequestBody UpdateStatusRequestDto updateStatusRequestDto) {
        Reimbursement reimb = reimbService.updateStatus(updateStatusRequestDto.id(), updateStatusRequestDto.status());
        return ResponseEntity.status(200).body(reimb);
    }

}

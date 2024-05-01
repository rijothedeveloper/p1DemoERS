package com.revature.p1demoERS.services;

import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.model.Reimbursement;
import com.revature.p1demoERS.model.Status;

import java.util.List;

public interface ReimbService {

    Reimbursement addReimb (ReimbRequestDto reimbRequestDto);
    List<Reimbursement> getMyReimbursements ();

    List<Reimbursement> getStatusFilteredReimbursements(Status status);

    Reimbursement updateDescription(Long id, String description);

    List<Reimbursement> getAllReimbursements();
}

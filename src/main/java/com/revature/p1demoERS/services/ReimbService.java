package com.revature.p1demoERS.services;

import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.model.Reimbursement;

import java.util.List;

public interface ReimbService {

    Reimbursement addReimb (ReimbRequestDto reimbRequestDto);
    List<Reimbursement> getMyReimbursements ();
}

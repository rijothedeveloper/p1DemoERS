package com.revature.p1demoERS.services;

import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.dto.ReimbResponseDto;
import org.springframework.stereotype.Service;

public interface ReimbService {

    ReimbResponseDto addReimb (ReimbRequestDto reimbRequestDto);
}

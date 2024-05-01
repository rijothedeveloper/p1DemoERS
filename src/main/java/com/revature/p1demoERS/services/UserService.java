package com.revature.p1demoERS.services;

import com.revature.p1demoERS.dto.SignupRequestDto;
import com.revature.p1demoERS.dto.SignupResponseDto;

public interface UserService {
    SignupResponseDto createUser(SignupRequestDto user);
}

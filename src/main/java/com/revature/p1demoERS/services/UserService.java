package com.revature.p1demoERS.services;

import com.revature.p1demoERS.dto.UserRequestDto;
import com.revature.p1demoERS.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto user);
}

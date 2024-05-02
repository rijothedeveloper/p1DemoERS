package com.revature.p1demoERS.services;

import com.revature.p1demoERS.dto.SignupRequestDto;
import com.revature.p1demoERS.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    void deleteUser(Long id);
}

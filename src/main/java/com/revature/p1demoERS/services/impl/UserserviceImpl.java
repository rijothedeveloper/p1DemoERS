package com.revature.p1demoERS.services.impl;

import com.revature.p1demoERS.dao.UserDao;
import com.revature.p1demoERS.dto.UserRequestDto;
import com.revature.p1demoERS.dto.UserResponseDto;
import com.revature.p1demoERS.model.User;
import com.revature.p1demoERS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserserviceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userDto) {
        if(userDao.findUserByUsername(userDto.username()).isPresent()){
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setRole(userDto.role());
        userDao.save(user);
        UserResponseDto userResponseDto = new UserResponseDto(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getRole());

        return userResponseDto;
    }
}

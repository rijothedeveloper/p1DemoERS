package com.revature.p1demoERS.services.impl;

import com.revature.p1demoERS.dao.UserDao;
import com.revature.p1demoERS.dto.SignupRequestDto;
import com.revature.p1demoERS.dto.UserResponseDto;
import com.revature.p1demoERS.model.User;
import com.revature.p1demoERS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserserviceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userDao.findAll();
        List<UserResponseDto> userDtoList = users.stream()
                .map(user -> new UserResponseDto( user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole()))
                .toList();
        return userDtoList;
    }
}

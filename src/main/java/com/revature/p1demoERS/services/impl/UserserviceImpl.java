package com.revature.p1demoERS.services.impl;

import com.revature.p1demoERS.dao.ReimbDao;
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


    private UserDao userDao;
    private ReimbDao reimbDao;

    @Autowired
    public UserserviceImpl(UserDao userDao, ReimbDao reimbDao) {
        this.userDao = userDao;
        this.reimbDao = reimbDao;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userDao.findAll();
        List<UserResponseDto> userDtoList = users.stream()
                .map(user -> new UserResponseDto( user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole()))
                .toList();
        return userDtoList;
    }

    @Override
    public void deleteUser(Long id) {
        reimbDao.deleteReimbs(id);
        userDao.deleteById(id);
    }
}

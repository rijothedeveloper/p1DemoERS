package com.revature.p1demoERS.services.impl;

import com.revature.p1demoERS.dao.ReimbDao;
import com.revature.p1demoERS.dao.UserDao;
import com.revature.p1demoERS.dto.ReimbRequestDto;
import com.revature.p1demoERS.dto.ValidationErrorDto;
import com.revature.p1demoERS.exception.UserNotFoundException;
import com.revature.p1demoERS.model.Reimbursement;
import com.revature.p1demoERS.model.Status;
import com.revature.p1demoERS.model.User;
import com.revature.p1demoERS.services.ReimbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReimbserviceImpl implements ReimbService {


    @Autowired
    private  ReimbDao reimbDao;
    @Autowired
    private UserDao userDao;

    public ReimbserviceImpl(ReimbDao reimbDao, UserDao userDao) {
        this.reimbDao = reimbDao;
        this.userDao = userDao;
    }

    @Override
    public Reimbursement addReimb(ReimbRequestDto reimbRequestDto) {


        com.revature.p1demoERS.model.Reimbursement reimb = new com.revature.p1demoERS.model.Reimbursement();

        reimb.setDescription(reimbRequestDto.description());

        reimb.setAmount(reimbRequestDto.amount());

        reimb.setStatus(Status.PENDING);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        reimb.setUser(user);

        reimbDao.save(reimb);

        return reimb;
    }

    @Override
    public List<Reimbursement> getMyReimbursements() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        List<com.revature.p1demoERS.model.Reimbursement> reimbursements = reimbDao.findByUserUserId(user.getUserId());

        return reimbursements;
    }

    @Override
    public List<Reimbursement> getStatusFilteredReimbursements(Status status) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findUserByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        List<com.revature.p1demoERS.model.Reimbursement> reimbursements = reimbDao.findByUserUserIdAndStatus(user.getUserId(), status);
        return reimbursements;
    }

}

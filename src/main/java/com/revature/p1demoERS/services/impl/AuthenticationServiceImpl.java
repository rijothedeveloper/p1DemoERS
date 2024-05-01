package com.revature.p1demoERS.services.impl;

import com.revature.p1demoERS.dao.UserDao;
import com.revature.p1demoERS.dto.*;
import com.revature.p1demoERS.exception.UserNotFoundException;
import com.revature.p1demoERS.model.User;
import com.revature.p1demoERS.services.AuthenticationService;
import com.revature.p1demoERS.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Override
    public UserResponseDto signup(SignupRequestDto signupRequest) {

        if(userDao.findUserByUsername(signupRequest.username()).isPresent()) {
            return null;
        }
        User user = new User();
        user.setUsername(signupRequest.username());
        user.setEmail(signupRequest.email());
        user.setFirstName(signupRequest.firstName());
        user.setLastName(signupRequest.lastName());
        user.setRole(signupRequest.role());
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        var savedUser = userDao.save(user);
        UserResponseDto signupResponseDto = new UserResponseDto(savedUser.getFirstName(), savedUser.getLastName(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
        return signupResponseDto;
    }

    @Override
    public SignInResponseDto signin(SigninRequestDto signinRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.username(), signinRequest.password()));
        } catch (Exception e) {
            return new SignInResponseDto(null, null);
//            throw new UserNotFoundException("User not found");
        }
        var user = userDao.findUserByUsername(signinRequest.username())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        SignInResponseDto signInResponseDto = new SignInResponseDto(jwt, refreshToken);
        return signInResponseDto;
    }

    @Override
    public SignInResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        String username = jwtService.extractUserName(refreshTokenRequest.token());
        User user = userDao.findUserByUsername(username)
                .orElseThrow();
        if(!jwtService.isTokenValid(refreshTokenRequest.token(), user)) {
            return null;
        }
        var jwt = jwtService.generateToken(user);
        SignInResponseDto jwtAuthenticationResponse = new SignInResponseDto(jwt, refreshTokenRequest.token());
        return jwtAuthenticationResponse;
    }
}

package com.revature.p1demoERS.services;

import com.revature.p1demoERS.dto.*;

public interface AuthenticationService {
    SignupResponseDto signup(SignupRequestDto signupRequest);
    SignInResponseDto signin(SigninRequestDto signinRequest);
    SignInResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest);

//    EmailVerificationResponse verifyRegisterationEmail(String token);

}

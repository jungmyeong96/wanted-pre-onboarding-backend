package com.wanted.jungproject.domain.user.application;

import com.wanted.jungproject.domain.auth.dto.TokenInfoRequest;
import com.wanted.jungproject.domain.auth.dto.TokenInfoResponse;
import com.wanted.jungproject.domain.user.dto.UserSignInRequest;
import com.wanted.jungproject.domain.user.dto.UserSignUpRequest;
import org.springframework.validation.Errors;

import java.util.Map;

public interface IUsersService {
    public Long signUp(UserSignUpRequest userSignUpRequest)  throws Exception;
    public TokenInfoResponse signIn(UserSignInRequest userSignInRequest) ;
    public TokenInfoResponse refresh(TokenInfoRequest tokenInfoRequest);
    public Map<String, String> validateHandling(Errors errors);
}

package com.wanted.jungproject.domain.user.application;

import com.wanted.jungproject.domain.user.dto.UserSignUp;
import org.springframework.validation.Errors;

import java.util.Map;

public interface IUsersService {
    public Long signUp(UserSignUp userSignUp)  throws Exception;
    public Map<String, String> validateHandling(Errors errors);
}

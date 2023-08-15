package com.wanted.jungproject.domain.user.application;

import com.wanted.jungproject.domain.user.domain.Role;
import com.wanted.jungproject.domain.user.domain.Users;
import com.wanted.jungproject.domain.user.domain.UsersRepository;
import com.wanted.jungproject.domain.user.dto.UserSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements IUsersService{

    private final UsersRepository usersRepository;
    public Long signUp(UserSignUp userSignUp) {
        Users users = Users.builder()
                .name(userSignUp.getName())
                .email(userSignUp.getEmail())
                .passwrod(userSignUp.getPassword())
                .address(userSignUp.getAddress())
                .role(Role.USER)
                .build();
        return usersRepository.save(users).getId();
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}

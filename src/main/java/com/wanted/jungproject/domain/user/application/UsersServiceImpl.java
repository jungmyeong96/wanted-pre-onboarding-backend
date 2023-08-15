package com.wanted.jungproject.domain.user.application;

import com.wanted.jungproject.domain.user.domain.Role;
import com.wanted.jungproject.domain.user.domain.Users;
import com.wanted.jungproject.domain.user.domain.UsersRepository;
import com.wanted.jungproject.domain.user.dto.UserSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Transactional
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements IUsersService{

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;
    public Long signUp(UserSignUp userSignUp) throws Exception {
        if(this.isEmailExist(userSignUp.getEmail())) {
            throw new Exception("Your Mail already Exist.");
        }
        Users users = userSignUp.toEntity();
        users.hashPassword(bCryptPasswordEncoder);
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

    private boolean isEmailExist(String email) {
        Optional<Users> byEmail = usersRepository.findByEmail(email);
        return !byEmail.isEmpty();
    }
}

package com.wanted.jungproject.domain.user.application;

import com.wanted.jungproject.domain.auth.application.TokenProvider;
import com.wanted.jungproject.domain.auth.domain.RefreshToken;
import com.wanted.jungproject.domain.auth.domain.RefreshTokenRepository;
import com.wanted.jungproject.domain.auth.dto.TokenInfoRequest;
import com.wanted.jungproject.domain.auth.dto.TokenInfoResponse;
import com.wanted.jungproject.domain.user.domain.Users;
import com.wanted.jungproject.domain.user.domain.UsersRepository;
import com.wanted.jungproject.domain.user.dto.UserSignInRequest;
import com.wanted.jungproject.domain.user.dto.UserSignUpRequest;
//import com.wanted.jungproject.domain.userDetail.application.CustomUsersDetailService;
import com.wanted.jungproject.global.exception.AppException;
import com.wanted.jungproject.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.
        AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private final TokenProvider tokenProvider;

    @Autowired
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private final CustomUsersDetailService customUserDetailService;
    public Long signUp(UserSignUpRequest userSignUpRequest) throws Exception {
        if(this.isEmailExist(userSignUpRequest.getEmail())) {
            throw new Exception("Your Mail already Exist.");
        }
        Users users = userSignUpRequest.toEntity();
        users.hashPassword(bCryptPasswordEncoder);
        return usersRepository.save(users).getId();
    }

//    public TokenInfoResponse signIn(UserSignInRequest userSignInRequest) {
//        // 1. ID(email)/PW 기반으로 AuthenticationToken 생성
//        System.out.println("1");
//        UsernamePasswordAuthenticationToken authenticationToken = userSignInRequest.toAuthentication();
//
//        Users foundUser = validateAndFindUserByEmail(userSignInRequest.getEmail());
//
//        validatePassword(userSignInRequest, foundUser);
//
//        System.out.println("2");
//        // 2. 실제 검증 로직(사용자 비밀번호 체크)
//        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행됨
//
//        UserDetails userDetails = customUserDetailService.loadUserByUsername(foundUser.getEmail());
//
//        System.out.println("3");
//        // 3. 인증 정보를 기반으로 JWT토큰 생성
//        TokenInfoResponse tokenInfoResponse = tokenProvider.generateTokenDto(userDetails);
//
//        System.out.println("4");
//        // 4. RefreshToken 저장
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(userDetails.getUsername())
//                .value(tokenInfoResponse.getRefreshToken())
//                .build();
//
//        System.out.println("5");
//
////        refreshTokenRepository.save(refreshToken);
//
//        System.out.println("6");
//        // 5. 토큰 발급
//        return tokenInfoResponse;
//    }

    /**
     * 해당 Email로 가입된 회원이 있는지 검증
     */
    private Users validateAndFindUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * 로그인 요청 시, 전달한 비밀번호가 DB에 저장되어있는 비밀번호와 일치하는지 확인
     */

//    private void validatePassword(UserSignInRequest request, Users foundUser) {
//        if (!bCryptPasswordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
//            throw new AppException(ErrorCode.WRONG_PASSWORD);
//        }
//    }

//    public TokenInfoResponse refresh(TokenInfoRequest tokenInfoRequest) {
//        // 임시.. 에러방지
//
//        UserDetails userDetails = customUserDetailService.loadUserByUsername(tokenInfoRequest.toString());
//        TokenInfoResponse tokenDto = tokenProvider.generateTokenDto(userDetails);
//
//
//        // 토큰 재발급
//        return tokenDto;
//    }

//    public Map<String, String> validateHandling(Errors errors) {
//        Map<String, String> validatorResult = new HashMap<>();
//
//        for (FieldError error : errors.getFieldErrors()) {
//            String validKeyName = String.format("valid_%s", error.getField());
//            validatorResult.put(validKeyName, error.getDefaultMessage());
//        }
//        return validatorResult;
//    }

    private boolean isEmailExist(String email) {
        Optional<Users> byEmail = usersRepository.findByEmail(email);
        return !byEmail.isEmpty();
    }
}

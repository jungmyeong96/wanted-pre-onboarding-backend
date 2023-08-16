package com.wanted.jungproject.domain.user.api;

import com.wanted.jungproject.domain.auth.dto.TokenInfoRequest;
import com.wanted.jungproject.domain.auth.dto.TokenInfoResponse;
import com.wanted.jungproject.domain.user.application.IUsersService;
import com.wanted.jungproject.domain.user.dto.UserSignInRequest;
import com.wanted.jungproject.domain.user.dto.UserSignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Tag(name = "Users", description = "users API Document")
public class UsersController {
    private final IUsersService usersServiceImpl;

    @GetMapping("/users/new") //회원가입 입력 폼
    @Operation(summary = "회원 가입 정보 입력폼 요청", description = "각종 개인정보를 입력할 양식을 전달합니다", tags = {"Users"})
    public String createUsersForm() {
        return "users/createUsersForm";
    }

    @PostMapping("/users/signup") //회원가입 정보 전달 메인 페이지로 이동
    @Operation(summary = "회원 가입 정보 저장", description = "각종 개인정보를 전달합니다", tags = {"Users"})
    public ResponseEntity<String> createUsers(@RequestBody @Valid UserSignUpRequest userSignUpRequest, Errors errors, Model model) throws Exception {
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userSignUp", userSignUpRequest);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = usersServiceImpl.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 다시 리턴 */
            return  new ResponseEntity("users/new", HttpStatus.BAD_REQUEST);
        }
        Long userId = usersServiceImpl.signUp(userSignUpRequest) ;
        return ResponseEntity.ok("mainPage");
    }

    @PostMapping("/users/signin")
    @Operation(summary = "로그인", description = "이메일과 비밀번호 전달", tags = {"Users"})
    public ResponseEntity<TokenInfoResponse> login(@RequestBody UserSignInRequest userSignInRequest) throws Exception {
        return ResponseEntity.ok(usersServiceImpl.signIn(userSignInRequest));
    }

    @PostMapping("/users/refresh")
    @Operation(summary = "토큰 갱신", description = "토큰 갱신하기", tags = {"Users"})
    public ResponseEntity<TokenInfoResponse> refresh(@RequestBody TokenInfoRequest tokenInfoRequest) {
        return ResponseEntity.ok(usersServiceImpl.refresh(tokenInfoRequest));
    }
}

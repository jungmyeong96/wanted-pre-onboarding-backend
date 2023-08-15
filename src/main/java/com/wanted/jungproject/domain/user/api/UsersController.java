package com.wanted.jungproject.domain.user.api;

import com.wanted.jungproject.domain.user.application.IUsersService;
import com.wanted.jungproject.domain.user.domain.UsersRepository;
import com.wanted.jungproject.domain.user.dto.UserSignUp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/users/new") //회원가입 정보 전달 메인 페이지로 이동
    @Operation(summary = "회원 가입 정보 저장", description = "각종 개인정보를 전달합니다", tags = {"Users"})
    public ResponseEntity<String> createUsers(@RequestBody @Valid UserSignUp userSignUp, Errors errors, Model model) throws Exception {
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userSignUp", userSignUp);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = usersServiceImpl.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 다시 리턴 */
            return  new ResponseEntity<>("users/createUsersForm", HttpStatus.BAD_REQUEST);
        }
        Long userId = usersServiceImpl.signUp(userSignUp) ;
        return new ResponseEntity<>("mainPage", HttpStatus.OK);
    }
}

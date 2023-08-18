package com.wanted.jungproject.landing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
@Tag(name = "landing", description = "landing API Document")
public class LandingController {


    @GetMapping("/")
    @Operation(summary = "기본 화면", description = "기본 화면을 출력합니다.", tags = {"View"})
    public String hello(Model model){
        model.addAttribute("hello","서버에서 보내준 값입니다");
        return "hello";
    }

    @GetMapping("/board/mainpage")
    @Operation(summary = "게시판 화면", description = "게시판 화면을 출력합니다.", tags = {"View"})
    public String mainPage(Model model){
        return "mainPage";
    }

    @GetMapping("/signinform")
    @Operation(summary = "로그인 화면", description = "로그인 화면을 출력합니다.", tags = {"View"})
    public String login(Model model){
        model.addAttribute("hello","서버에서 보내준 값입니다");
        return "signInForm";
    }

    @GetMapping("/signupform")
    @Operation(summary = "회원가입 화면", description = "회원가입 화면을 출력합니다.", tags = {"View"})
    public String join(Model model){
        model.addAttribute("hello","서버에서 보내준 값입니다");
        return "signUpForm";
    }
}

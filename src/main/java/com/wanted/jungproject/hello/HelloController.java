package com.wanted.jungproject.hello;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/")
@Tag(name = "hello", description = "hello API Document")
public class HelloController {


    @GetMapping("/")
    @Operation(summary = "기본 화면", description = "기본 화면을 출력합니다.", tags = {"View"})
    public String hello(Model model){
        model.addAttribute("hello","서버에서 보내준 값입니다");
        return "hello";
    }

    @GetMapping("/login")
    @Operation(summary = "기본 화면", description = "기본 화면을 출력합니다.", tags = {"View"})
    public String login(Model model){
        model.addAttribute("hello","서버에서 보내준 값입니다");
        return "login";
    }
}

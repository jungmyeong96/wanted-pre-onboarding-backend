package com.wanted.jungproject.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model){
        model.addAttribute("hello","서버에서 보내준 값입니다");
        return "hello";
    }
}

package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class ShowController {
    @GetMapping
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}

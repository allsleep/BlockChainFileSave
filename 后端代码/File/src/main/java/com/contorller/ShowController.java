package com.contorller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ShowController {

    @GetMapping("/upload")
    public String showUpload(){
        return null;
    }
}

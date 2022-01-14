package com.controller;

import com.pojo.LoginInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class tokenController {
    @GetMapping("/getToken")
    public com.pojo.Result<Object> getToken(@RequestBody LoginInfo loginInfo){
        return null;
    }
}

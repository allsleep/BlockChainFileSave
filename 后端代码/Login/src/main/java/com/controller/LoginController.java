package com.controller;

import com.CommonMethods.FileHash;
import com.alibaba.fastjson.JSONObject;
import com.pojo.LoginInfo;
import com.pojo.Result;
import com.pojo.StatusCode;
import com.service.LoginService;
import com.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        return new ModelAndView("signup");
    }

    @GetMapping("/test")
    public Result<String> getToken(@RequestBody LoginInfo loginInfo){
        String token = JwtUtil.createJWT(loginInfo.getAccountId(), loginInfo.getPerson().toString(), null);
//        JSONObject json = new JSONObject();
//        json.put("token", token);
        return new com.pojo.Result<>(true, StatusCode.OK, "获取成功", token);
    }
}

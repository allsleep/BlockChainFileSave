package com.controller;

import com.CommonMethods.IdWorker;
import com.alibaba.fastjson.JSONObject;
import com.pojo.Result;
import com.pojo.StatusCode;
import com.receive.receiveBody;
import com.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private IdWorker idWorker;

    @PostMapping("/signup")
    public Result<Object> signup_api(@RequestBody receiveBody rec) {
        String accountId = String.valueOf(idWorker.nextId());
        if (!loginService.isUserExist(rec.getUsername()))
            return new Result(false, StatusCode.LOGIN_ERROR, "该用户名已经注册");
        if (loginService.addUser(accountId, rec.getUsername(), rec.getPassword()) &&
                loginService.addUserInfo(accountId, rec.getAuthor(), rec.getIdCard(), rec.getPhoneNumber(), new Date()))
            return new Result("注册成功");
        else
            return new Result(false, StatusCode.LOGIN_ERROR, "注册失败");
    }

    @GetMapping("/login")
    public Result<Object> login_api(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (loginService.isUserExist(username))
            return new Result(false, StatusCode.LOGIN_ERROR, "用户不存在");
        if (loginService.login(username, password)) {
            JSONObject json = new JSONObject();
            json.put("token", loginService.getToken(
                    loginService.getUserInfo(loginService.getUser(username).getAccountId())
            ));
            return new Result(true,StatusCode.OK,"登录成功", json);
        }
        else
            return new Result(false, StatusCode.LOGIN_ERROR, "密码错误");
    }
}

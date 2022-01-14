package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.pojo.LoginInfo;
import com.pojo.Result;
import com.pojo.StatusCode;
import com.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {
    @GetMapping("/getToken")
    public Result<JSONObject> getToken(@RequestBody LoginInfo loginInfo){
        String token = JwtUtil.createJWT(loginInfo.getAccountId(), loginInfo.getPerson().toString(), null);
        JSONObject json = new JSONObject();
        json.put("token", token);
        return new com.pojo.Result<>(true, StatusCode.OK, "获取成功", json);
    }
}

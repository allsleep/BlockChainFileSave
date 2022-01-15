package com.service;


import com.mapper.LoginMapper;
import com.pojo.LoginInfo;
import com.pojo.User;
import com.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public boolean login(String username, String password) {
        User user = loginMapper.findUser(username);
        if (user == null){
            return false;
        }else {
            return BCrypt.checkpw(password, user.getPassword());
        }
    }

    public String getToken(LoginInfo loginInfo){
        return JwtUtil.createJWT(loginInfo.getAccountId(), loginInfo.toString(), null);
    }

    public LoginInfo getUserInfo(String accountId) {
        return loginMapper.findLoginInfo(accountId);
    }
    public User getUser(String username) {
        return loginMapper.findUser(username);
    }

    public boolean addUser(String accountId, String username, String password){
        User new_user = new User();
        new_user.setAccountId(accountId);
        new_user.setUsername(username);
        //对密码进行加密
        String gensalt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw(password, gensalt);
        new_user.setPassword(hashpw);
        return loginMapper.addUser(new_user);
    }

    public boolean addUserInfo(String accountId, String author, String idCard, String phoneNumber, Date date){
        LoginInfo loginInfo = new LoginInfo(accountId, author, idCard, phoneNumber, date);
        return loginMapper.addUserInfo(loginInfo);
    }

    public boolean isUserExist(String username){
        User user = loginMapper.findUser(username);
        return user == null;
    }
}

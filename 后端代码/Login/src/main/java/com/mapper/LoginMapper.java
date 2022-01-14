package com.mapper;

import com.pojo.LoginInfo;
import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Repository
public interface LoginMapper {
    boolean addUser(User user);
    boolean addUserInfo(LoginInfo loginInfo);
    User findUser(String username);
    LoginInfo findLoginInfo(String accountId);
}

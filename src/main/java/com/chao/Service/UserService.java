package com.chao.Service;

import com.chao.Pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 验证用户
     */
    User queryUser(String username, String password);

    List<User> getUser();
}

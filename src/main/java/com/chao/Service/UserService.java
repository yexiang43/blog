package com.chao.Service;

import com.chao.Pojo.User;

public interface UserService {

    /**
     * 验证用户
     */
    User queryUser(String username, String password);
}

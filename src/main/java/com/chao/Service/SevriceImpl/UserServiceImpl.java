package com.chao.Service.SevriceImpl;

import com.chao.Dao.UserRepository;
import com.chao.Pojo.User;
import com.chao.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User queryUser(String username, String password) {
        //密码加密
        return userRepository.findByUsernameAndPassword( username, DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }
}

package com.onezhan.service.impl;

import com.onezhan.mapper.UserMapper;
import com.onezhan.pojo.User;
import com.onezhan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getByUserName(String username) {
        return userMapper.getByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        userMapper.register(username, password);
    }

    @Override
    public void updateMsg(User user) {
        userMapper.updateMsg(user);
    }

    @Override
    public void changePwd(String username, String newPwd) {
        userMapper.changePwd(username, newPwd);
    }
}

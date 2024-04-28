package com.onezhan.service;

import com.onezhan.pojo.User;

public interface UserService {
    User getByUserName(String username);
    void register(String username, String password);
}

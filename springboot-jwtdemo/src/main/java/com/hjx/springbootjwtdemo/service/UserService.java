package com.hjx.springbootjwtdemo.service;

import com.hjx.springbootjwtdemo.entity.User;

public interface UserService {
    User  findByUserId(String userid);

    User findByUsername(User user);
}

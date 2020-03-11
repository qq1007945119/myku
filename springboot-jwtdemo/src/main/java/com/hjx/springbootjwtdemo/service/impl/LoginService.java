package com.hjx.springbootjwtdemo.service.impl;

import com.hjx.springbootjwtdemo.entity.User;
import com.hjx.springbootjwtdemo.mapper.Userdao;
import com.hjx.springbootjwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserService {
    @Autowired
    private Userdao  userdao;
    @Override
    public User findByUserId(String userid) {

        return  userdao.findUserById(userid);
    }

    @Override
    public User findByUsername(User user) {
        return userdao.findByUsername(user.getUsername());
    }

}

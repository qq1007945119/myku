package com.hjx.springbootjwtdemo;

import com.hjx.springbootjwtdemo.mapper.Userdao;
import com.hjx.springbootjwtdemo.entity.User;
import com.hjx.springbootjwtdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = SpringbootJwtdemoApplication.class)
class SpringbootJwtdemoApplicationTests {
    @Autowired
    private  Userdao  userdao;
    @Autowired
    private UserService  userService;

    @Test
    void contextLoads() {
        User user = userdao.findByUsername("张三");
        System.out.println(user.getUsername());
    }

}

package com.hjx.springbootjwtdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.hjx.springbootjwtdemo.annotction.UserLoginToken;
import com.hjx.springbootjwtdemo.entity.User;
import com.hjx.springbootjwtdemo.service.UserService;
import com.hjx.springbootjwtdemo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
UserController
*/
@RestController
@RequestMapping("api")
public class UserController {
      @Autowired
      private UserService  userService;
	  //使用TOken
      @Autowired
      private TokenUtil tokenUtil;
      @PostMapping("/login")
      public Object  login(User user)
      {

            JSONObject jsonObject = new JSONObject();
            User user1 = userService.findByUsername(user);
            if (user1 == null)
            {
                  jsonObject.put("message","登录失败,用户不存在");
                  return jsonObject;
            }else {
                  if (!user.getPassword().equals(user1.getPassword()))
                  {
                        jsonObject.put("message","登录失败,密码错误");
                        return jsonObject;
                  }else
                  {
						//用户第一次登录成功后，将用户的信息加密即token后再传回给前端
                        String token = tokenUtil.getToken(user1);
                        jsonObject.put("token", token);
                        jsonObject.put("user", user1);
                        return jsonObject;
                  }
            }

      }

      @UserLoginToken
      @GetMapping("/getMessage")
      public String getMessage(){
            return "你已通过验证";
      }


}

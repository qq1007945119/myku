package com.hjx.springbootjwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hjx.springbootjwtdemo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    public  String  getToken(User user)
    {
        String  token="";
        token= JWT.create().withAudience(user.getId())
                    .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}

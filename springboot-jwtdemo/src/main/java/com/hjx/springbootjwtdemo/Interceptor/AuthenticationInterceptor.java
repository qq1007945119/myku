package com.hjx.springbootjwtdemo.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hjx.springbootjwtdemo.annotction.PassToken;
import com.hjx.springbootjwtdemo.annotction.UserLoginToken;
import com.hjx.springbootjwtdemo.entity.User;
import com.hjx.springbootjwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
拦截器，实现HandlerInterceptor
用于验证方法的注解，是否需要token验证


*/
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String  token  =  request.getHeader("token");

        //如果不是映射到方法直接通过，可以通过url来访问静态资源
        if(!(object instanceof HandlerMethod))
        {
            return  true;
        }


        HandlerMethod handlerMethod= (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class))
        {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.reqired())
            {
                return  true;
            }
        }
        
		//检查有没有需要用户权限的注释
        if (method.isAnnotationPresent(UserLoginToken.class))
        {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required())
            {
                //执行认证
                if (token == null)
                {
                    throw  new RuntimeException("无token ，请重新登录");
                }

                //获取token中的user id
                String userid;
                try {
                   userid =  JWT.decode(token).getAudience().get(0);
                }catch (JWTDecodeException j)
                {
                    throw  new RuntimeException("401");
                }
                User user = userService.findByUserId(userid);
                if (user == null)
                {
                    throw  new RuntimeException("用户不存在！请重新登录");
                }

                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try{
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e)
                {
                    throw  new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

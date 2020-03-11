package com.hjx.springbootjwtdemo.config;


import com.hjx.springbootjwtdemo.Interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
配置类
添加拦截器类
将拦截器类进行依赖注入
也可以在拦截器中使用@Compent注解
在此类中注入进行，添加到拦截器中即可
*/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(authenticationInterceptor())
                     .addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor  authenticationInterceptor()
    {
        return new AuthenticationInterceptor();
    }
}

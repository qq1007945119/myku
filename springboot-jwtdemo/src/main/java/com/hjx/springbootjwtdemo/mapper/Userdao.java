package com.hjx.springbootjwtdemo.mapper;

import com.hjx.springbootjwtdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Userdao {
    User findByUsername(@Param("name") String username);
    User  findUserById(@Param("userid") String userid);
}

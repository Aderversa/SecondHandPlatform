package com.onezhan.mapper;

import com.onezhan.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User getByUserName(String username);
    void register(@Param("username")String username, @Param("password")String password);
}

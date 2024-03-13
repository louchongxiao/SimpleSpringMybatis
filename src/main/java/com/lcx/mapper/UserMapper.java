package com.lcx.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public interface UserMapper {

    @Select("SELECT 'user'")
    String selectUserName();
}

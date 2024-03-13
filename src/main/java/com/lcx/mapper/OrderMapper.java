package com.lcx.mapper;

import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

    @Select("SELECT 'order'")
    String selectOrderName();
}

package com.lcx.service;

import com.lcx.mapper.OrderMapper;
import com.lcx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    public void test() {
        System.out.println(userMapper.selectUserName());System.out.println(orderMapper.selectOrderName());
    }
}

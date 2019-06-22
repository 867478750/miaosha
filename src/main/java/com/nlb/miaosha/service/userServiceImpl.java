package com.nlb.miaosha.service;


import com.nlb.miaosha.domain.user;
import com.nlb.miaosha.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService{
    @Autowired
    userMapper userMapper;


    @Override
    public user selectUserService(String name) {
        return userMapper.selectUser(name);
    }
}

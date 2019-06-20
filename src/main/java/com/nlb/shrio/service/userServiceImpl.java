package com.nlb.shrio.service;

import com.nlb.shrio.domain.user;
import com.nlb.shrio.mapper.userMapper;
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

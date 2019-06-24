package com.nlb.miaosha.controller;

import com.nlb.miaosha.domain.user;
import com.nlb.miaosha.service.jedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.Result;

import static com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleSizeExpr.Unit.T;

@Controller
public class redisController {

    @Autowired
    jedisService jedisService;

    @RequestMapping("/redis/get")
    public Result<user> redisGet(){
        jedisService.get(String,Class<T> clazz);
        return
    }
}

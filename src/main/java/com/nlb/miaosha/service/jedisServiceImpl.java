package com.nlb.miaosha.service;

import com.alibaba.fastjson.JSON;
import com.nlb.miaosha.config.redisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class jedisServiceImpl implements jedisService{

    @Autowired
    redisConfig redisConfig;

    @Autowired
    JedisPool jedisPool;

    @Override
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String s = jedis.get(key);
            T t = StringToBean(s,clazz);
            return t;
        }finally {
            closePool(jedisPool);
        }

    }

    @Override
    public <T> T set(String key, T value) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String s = BeanToString(value);
            String  = jedis.set(key, s);
            return t;
        }finally {
            closePool(jedisPool);
        }

    }

    private <T> String BeanToString(T value) {
        Class<?> Classzz = value.getClass();
        if(Classzz==int.class||Classzz==Integer.class){
            return ""+value;
        }else if(Classzz==String.class){
            return (String)value;
        }else if(Classzz==long.class||Classzz==Long.class){
            return ""+value;
        }else{
            return JSON.toJSONString(value);
        }
    }


    private <T> T StringToBean(String s,Class<T> clazz) {
    }

    private void closePool(JedisPool jedisPool) {
        if(jedisPool!=null){
            jedisPool.close();
        }
    }






    @Bean
    public JedisPool getjedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxActive());
        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWait());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisConfig.getHost(),Integer.parseInt(redisConfig.getPort()));
        return jedisPool;
    }
}

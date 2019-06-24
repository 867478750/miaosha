package com.nlb.miaosha.service;

public interface jedisService {
    <T> T get(String key,Class<T> clazz);
}

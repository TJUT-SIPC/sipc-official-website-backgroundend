package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 1.写入缓存
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean set(final String key, Object value){
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 2.写入缓存并设置有效时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    @Override
    public boolean set(String key, Object value, Long expireTime) {
        boolean result = false;
        try {

            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 3.批量删除对应的value
     * @param keys
     */
    @Override
    public void remove(String... keys) {
        for (String key : keys){
            remove(key);
        }
    }

    /**
     * 4.批量删除key
     * @param pattern
     */
    @Override
    public void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if(keys.size()>0)
            redisTemplate.delete(keys);
    }

    /**
     * 5.删除对应的value
     * @param key
     */
    @Override
    public void remove(String key) {
        if(exists(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 6.检验缓存中是否存在对应value
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 7.读取缓存
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);

        if (result!=null) return result.toString();

        return null;
    }

}

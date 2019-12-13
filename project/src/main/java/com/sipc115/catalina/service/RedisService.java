package com.sipc115.catalina.service;


public interface RedisService {

     boolean set (final String key, Object value);

     boolean set( final String key, Object value, Long expireTime);

     void remove(final String... keys);

     void removePattern(final String pattern);

     void remove(final String key);

     boolean exists(final String key);

     String get(final String key);


}

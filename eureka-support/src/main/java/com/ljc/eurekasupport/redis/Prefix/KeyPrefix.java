package com.ljc.eurekasupport.redis.Prefix;


/**
 * 缓存的前缀接口
 */
public interface KeyPrefix {

    //有效期
    public int getExpireSeconds();

    //前缀
    public String getPrefix();
}

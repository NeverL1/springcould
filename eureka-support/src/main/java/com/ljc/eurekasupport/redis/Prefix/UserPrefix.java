package com.ljc.eurekasupport.redis.Prefix;

/**
 * @program: springcould
 * @description:
 * @author: LJC
 * @create: 2022-06-30 18:23
 **/
public class UserPrefix extends BasePrefix{
    public UserPrefix(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static UserPrefix userPrefix = new UserPrefix(3600,"user");
}

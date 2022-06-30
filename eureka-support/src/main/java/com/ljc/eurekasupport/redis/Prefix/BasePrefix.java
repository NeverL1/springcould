package com.ljc.eurekasupport.redis.Prefix;

public abstract class BasePrefix implements KeyPrefix {

    //有效期
    private int expireSeconds;
    //前缀
    private String prefix;

    public BasePrefix(){}

    public BasePrefix(String prefix) {
        expireSeconds = 0;
        this.prefix = prefix;
    }



    public BasePrefix(int expireSeconds,String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    //默认为0代表永不过期
    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds){
        this.expireSeconds = expireSeconds;
    }


    //前缀为类名:+prefix
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":"+prefix;
    }
}

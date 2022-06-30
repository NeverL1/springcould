package com.ljc.eurekasupport.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

@Service
@Slf4j
public class RedisPoolFactory {

    @Resource
    RedisConfig redisConfig;

    //JedisPool的实例注入到spring容器里面
    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        log.info("redisConfig.getPoolMaxldle():{}", redisConfig.getPoolMaxldle());
        log.info("redisConfig.getPoolMaxTotal():{}", redisConfig.getPoolMaxldle());
        log.info("redisConfig.getPoolMaxWait():{}", redisConfig.getPoolMaxWait());
        log.info("redisConfig.getPassword():{}", redisConfig.getPassword());

        poolConfig.setMaxIdle(redisConfig.getPoolMaxldle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);//s-->ms
        //因为我们使用的是s（秒）来配置的，而源码使用的是ms（毫秒），所以转换一下
        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
        return jp;
    }
}

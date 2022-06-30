package com.ljc.eurekasupport.redis;


import com.ljc.eurekasupport.redis.Prefix.KeyPrefix;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RedisService {

    @Resource
    JedisPool jedisPool;

    public <T> T get(KeyPrefix prefix, String key, Class<T> data) {
        log.info("RedisService_Redis_GET");
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            log.info("RedisService_Redis_realKey:{}",realKey);
            String value = jedis.get(realKey);
            log.info("RedisService_Redis_value:{}",value);
            return stringToBean(value,data);
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean set(KeyPrefix prefix,String key,T value){
        log.info("RedisService_Redis_SET");
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            log.info("RedisService_Redis_Key:{}",key);
            log.info("RedisService_Redis_realKey:{}",realKey);
            //对象转为json字符串
            String s = beanToString(value);
            if(s==null||s.length()<=0) {
                return false;
            }
            //查看是否设置过期时间
            int seconds = prefix.getExpireSeconds();
            if (seconds <= 0) {
                jedis.set(realKey,s);
            }else{
                jedis.setex(realKey, seconds,s);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean setRange(KeyPrefix prefix,String key,T value){
        log.info("RedisService_Redis_SET");
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            log.info("RedisService_Redis_Key:{}",key);
            log.info("RedisService_Redis_realKey:{}",realKey);
            //对象转为json字符串
            String s = beanToString(value);
            if(s==null||s.length()<=0) {
                return false;
            }
            jedis.setrange(realKey,0,s);
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 移除对象,删除
     * @param prefix
     * @param key
     * @return
     */
    public boolean delete(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String realKey=prefix.getPrefix()+key;
            long ret=jedis.del(realKey);
            return ret>0;//删除成功，返回大于0
            //return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        String string = beanToString(list);
        System.out.println(string);
        List<String> list1 = stringToBean(string, List.class);
        list1.add("3");
        System.out.println(list1.toString());

    }

//    public <T> Boolean setList(KeyPrefix prefix,String key,List<T> value){
//        log.info("RedisService_Redis_SETLIST");
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            String realKey = prefix.getPrefix() + key;
//            log.info("RedisService_Redis_Key:{}",key);
//            log.info("RedisService_Redis_realKey:{}",realKey);
//            //对象转为json字符串
//            String s = beanToString(value);
//            if(s==null||s.length()<=0) {
//                return false;
//            }
//            //查看是否设置过期时间
//            int seconds = prefix.getExpireSeconds();
//            if (seconds <= 0) {
//                jedis.lpush(realKey,s);
//            }else{
//                jedis.setex(realKey, seconds,s);
//            }
//            return true;
//        }finally {
//            returnToPool(jedis);
//        }
//    }

    /**
     * 减少值
     * @param prefix
     * @param key
     * @return
     */
    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String realKey=prefix.getPrefix()+key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * @param prefix
     * @param key
     * @return
     */
    public <T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String realKey=prefix.getPrefix()+key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }


    /**
     * 获得过期时间
     * @param prefix
     * @param key
     * @return
     */
    public Long getExpireTime(KeyPrefix prefix,String key){
        Jedis jedis=null;
        try {
            jedis=jedisPool.getResource();
            String realKey=prefix.getPrefix()+key;
            return jedis.ttl(realKey);
        }finally {
            returnToPool(jedis);
        }
    }
    /**
     * 还redis线程
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if(jedis!=null) {
            jedis.close();
        }
    }
    /**
     * 将字符串转换为Bean对象
     *
     * parseInt()返回的是基本类型int 而valueOf()返回的是包装类Integer
     * Integer是可以使用对象方法的  而int类型就不能和Object类型进行互相转换 。
     * int a=Integer.parseInt(s);
     Integer b=Integer.valueOf(s);
     */
    public static <T> T stringToBean(String s,Class<T> clazz) {
        if(s==null||s.length()==0||clazz==null) {
            return null;
        }
        if(clazz==int.class||clazz==Integer.class) {
            return ((T) Integer.valueOf(s));
        }else if(clazz==String.class) {
            return (T) s;
        }else if(clazz==long.class||clazz==Long.class) {
            return (T) Long.valueOf(s);
        }else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(s,clazz);
            } catch (JsonProcessingException e) {
                log.error("反序列化出错：{}",clazz.toString());
                return null;
            }
        }
    }

    /**
     * 将Bean对象转换为字符串类型
     * @param <T>
     */
    public static <T> String beanToString(T value) {
        //如果是null
        if(value==null) return null;
        //如果不是null
        Class<?> clazz=value.getClass();
        if(clazz==int.class||clazz==Integer.class) {
            return ""+value;
        }else if(clazz==String.class) {
            return ""+value;
        }else if(clazz==long.class||clazz==Long.class) {
            return ""+value;
        }else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                log.error("序列化出错：{}",clazz.toString());
                return null;
            }
        }
    }

}

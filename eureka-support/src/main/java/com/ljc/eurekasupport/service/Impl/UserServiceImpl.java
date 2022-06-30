package com.ljc.eurekasupport.service.Impl;

import com.ljc.eurekasupport.entity.User;
import com.ljc.eurekasupport.exception.GlobalException;
import com.ljc.eurekasupport.mapper.UserMapper;
import com.ljc.eurekasupport.redis.Prefix.BasePrefix;
import com.ljc.eurekasupport.redis.Prefix.UserPrefix;
import com.ljc.eurekasupport.redis.RedisService;
import com.ljc.eurekasupport.result.CodeMsg;
import com.ljc.eurekasupport.result.ResultCodeEnum;
import com.ljc.eurekasupport.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcould
 * @description:
 * @author: LJC
 * @create: 2022-06-30 16:11
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisService redisService;

    @Override
    public User find(int id) {
        User user = redisService.get(UserPrefix.userPrefix, id + "", User.class);
        if (user == null){
            user = userMapper.findUserById(id);
            if (user == null) {
                throw new GlobalException(new CodeMsg(ResultCodeEnum.ERROR));
            }
        }else{
            redisService.set(UserPrefix.userPrefix,user.getId()+"",user);
        }
        return user;
    }
}

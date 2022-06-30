package com.ljc.eurekaconsumer.contorller;

import com.ljc.eurekaconsumer.entity.User;
import com.ljc.eurekaconsumer.result.Result;
import com.ljc.eurekaconsumer.result.ResultCodeEnum;
import com.ljc.eurekaconsumer.sevice.UserTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcould
 * @description:
 * @author: LJC
 * @create: 2022-06-29 12:58
 **/
@RequestMapping("/consumer")
@RestController
public class ConsumerController {

    @Resource
    UserTemplate userTemplate;

    @ResponseBody
    @RequestMapping(value = "/getUser/{id}")
    public String getUer(@PathVariable int id) {
        Result<User> result = userTemplate.getUser(id);
        if (result.getCode().equals(ResultCodeEnum.ERROR.getCode())){
            return "无此用户";
        }

        String user = result.getData().toString();
        return "I'm 消费者"+ ", " +user;
    }
}

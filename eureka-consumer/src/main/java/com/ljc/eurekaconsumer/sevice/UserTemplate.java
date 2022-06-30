package com.ljc.eurekaconsumer.sevice;

import com.ljc.eurekaconsumer.entity.User;
import com.ljc.eurekaconsumer.result.Result;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springcould
 * @description:
 * @author: LJC
 * @create: 2022-06-30 16:04
 **/
@FeignClient("EUREKA-SUPPORT")
public interface UserTemplate {

    @GetMapping("/user/getUser")
    Result<User> getUser(int id);
}

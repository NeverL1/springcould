package com.ljc.eurekaserver;

import com.ljc.eurekasupport.entity.User;
import com.ljc.eurekasupport.result.Result;
import com.ljc.eurekasupport.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcould
 * @description:
 * @author: LJC
 * @create: 2022-06-28 12:13
 **/
@RestController
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/user/getUser")
    @ResponseBody
    public Result<User> getUser(int id){
        return Result.success(userService.find(id));
    }


}

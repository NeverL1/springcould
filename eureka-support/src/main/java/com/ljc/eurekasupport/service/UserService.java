package com.ljc.eurekasupport.service;

import com.ljc.eurekasupport.entity.User;
import org.springframework.stereotype.Service;

/**
 * @program: springcould
 * @description:
 * @author: LJC
 * @create: 2022-06-30 16:10
 **/
@Service
public interface UserService {

    User find(int id);
}

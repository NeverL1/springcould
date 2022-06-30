package com.ljc.eurekasupport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: springcould
 * @description:
 * @author: LJC
 * @create: 2022-06-30 17:55
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;

    private String userName;
}

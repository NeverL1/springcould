package com.ljc.eurekasupport.result;

/**
 * @program:
 * @description: 返回枚举类
 * @author: LJC
 * @create: 2022-04-02 14:57
 **/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResultCodeEnum {


    SUCCESS(20000,"SUCCESS"),
    BIND_ERROR(20002,"参数校验异常"),
    ERROR(20001,"ERROR");

    private final Integer code;
    private final String  message;

}

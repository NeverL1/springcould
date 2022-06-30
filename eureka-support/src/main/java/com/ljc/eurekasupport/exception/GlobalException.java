package com.ljc.eurekasupport.exception;

import com.ljc.eurekasupport.result.CodeMsg;

/**
 * @program:
 * @description: 自定义全局异常类
 * @author: LJC
 * @create: 2022-04-03 13:43
 **/
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg msg;

    public GlobalException(CodeMsg msg){
        this.msg = msg;
    }
    public CodeMsg getMsg() {
        return msg;
    }
    public void setCm(CodeMsg msg) {
        this.msg = msg;
    }

}

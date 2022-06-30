package com.ljc.eurekasupport.exception;

import com.ljc.eurekasupport.result.CodeMsg;
import com.ljc.eurekasupport.result.Result;
import com.ljc.eurekasupport.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @program:
 * @description: 全局异常处理类
 * @author: LJC
 * @create: 2022-04-03 13:52
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //拦截什么异常
    @ExceptionHandler(value=Exception.class)//拦截所有的异常
    @ResponseBody
    public Result<String> ExceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
            //全局异常
            GlobalException ex = (GlobalException) e;
            log.error("全局异常：{}",ex.getMsg());
            log.error(String.valueOf(ex.getCause()));
            CodeMsg msg = ex.getMsg();
            return Result.error(msg);
        }else if (e instanceof BindException){
            //参数校验异常
            BindException ex = (BindException) e;
            Result resultCode = Result.error(new CodeMsg(ResultCodeEnum.BIND_ERROR));
            resultCode.setMessage("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return resultCode;
        }else {
            log.error("未知异常:{}",e.toString());
            log.error(String.valueOf(e.getCause()));
            return Result.error(new CodeMsg(ResultCodeEnum.ERROR));
        }
    }

}

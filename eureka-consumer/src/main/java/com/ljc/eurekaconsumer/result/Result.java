package com.ljc.eurekaconsumer.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    //success
    private Result(T data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message =  ResultCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    //error
    private Result(CodeMsg codeMsg) {
        if(codeMsg==null) {
            return;
        }

        this.code=codeMsg.getCode();
        this.message=codeMsg.getMessage();
    }
    //error
    private Result(CodeMsg codeMsg,T data) {
        if(codeMsg==null) {
            return;
        }

        this.code=codeMsg.getCode();
        this.message=codeMsg.getMessage();
        this.data = data;
    }

    //成功
    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }

    // 失败
    public static <T> Result<T> error(CodeMsg sm) {//CodeMsg包含了code和msg
        return new Result<>(sm);
    }

    // 失败
    public static <T> Result<T> error(CodeMsg sm,T data) {//CodeMsg包含了code和msg
        return new Result<>(sm,data);
    }

}

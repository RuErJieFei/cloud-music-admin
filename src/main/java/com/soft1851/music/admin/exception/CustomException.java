package com.soft1851.music.admin.exception;

import com.soft1851.music.admin.common.ResultCode;

/**
 * @ClassName CustomException
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/4/21 4:22 下午
 * @Version 1.0
 **/
public class CustomException extends RuntimeException {
    protected ResultCode resultCode;

    public CustomException(String msg, ResultCode resultCode) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}


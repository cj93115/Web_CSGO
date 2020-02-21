package com.Web_CSGO.common.exception;


import com.Web_CSGO.common.HttpCode;

/**
 * @author Liangguisheng
 * @version 2017年11月23日
 */
public class IllegalParameterException extends BaseException {

    public IllegalParameterException() {
    }

    public IllegalParameterException(Throwable ex) {
        super(ex);
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable ex) {
        super(message, ex);
    }

    @Override
    protected HttpCode getHttpCode() {
        return HttpCode.BAD_REQUEST;
    }
}

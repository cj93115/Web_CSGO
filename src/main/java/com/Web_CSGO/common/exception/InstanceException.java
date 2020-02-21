package com.Web_CSGO.common.exception;

/**
 * @author chenlong
 * @version 2018年03月29日 18时18分
 */
@SuppressWarnings("serial")
public class InstanceException extends RuntimeException {
    public InstanceException() {
        super();
    }

    public InstanceException(Throwable t) {
        super(t);
    }
}

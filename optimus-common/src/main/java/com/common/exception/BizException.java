package com.common.exception;

/**
 * 自定义异常类
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(RuntimeException e) {
        super(e);
    }

    public BizException(Throwable e) {
        super(e);
    }

}

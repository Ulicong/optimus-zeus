package com.exception;

/**
 * Created by weber on 2017/4/16.
 * <p>
 * 自定异常类
 */
public class ExceptionLearning extends RuntimeException {


    public ExceptionLearning(Throwable e) {
        super(e);
    }

    public ExceptionLearning() {
        super();
    }

    public ExceptionLearning(String msg) {
        super(msg);
    }

    public ExceptionLearning(RuntimeException e) {
        super(e);
    }


}

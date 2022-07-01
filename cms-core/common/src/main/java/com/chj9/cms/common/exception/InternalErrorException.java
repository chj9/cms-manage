package com.chj9.cms.common.exception;

/**
 * @description:
 * @author: Administrator
 * @create: 2020-04-24 17:33
 **/
public class InternalErrorException extends RuntimeException {

    private static final long serialVersionUID = 9154826031539387476L;

    private Object payload;

    public InternalErrorException(String message, Object payload) {
        super(message);
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public static InternalErrorException of(String message, Object payload) {
        return new InternalErrorException(message, payload);
    }

    public static InternalErrorException of(Object payload) {
        return new InternalErrorException("抱歉，服务出错了，请稍后再试", payload);
    }

    public static InternalErrorException of() {
        return of(null);
    }

    public static void throwIfTrue(boolean condition) {
        if (condition) {
            throw of();
        }
    }

    public static void throwIfTrue(boolean condition, String msg) {
        if (condition) {
            throw of(msg);
        }
    }

    public static void throwIfTrue(boolean condition, String msg, Object payload) {
        if (condition) {
            throw of(msg, payload);
        }
    }

}

package com.chj9.cms.common.exception;

public class NoContentException extends RuntimeException {
    private final static long serialVersionUID = 5754203603847561631L;

    public NoContentException() {
    }

    public NoContentException(String message) {
        super(message);
    }

    public static void throwIfTrue(boolean condition, String msg) {
        if (condition) {
            throw new NoContentException(msg);
        }
    }
}

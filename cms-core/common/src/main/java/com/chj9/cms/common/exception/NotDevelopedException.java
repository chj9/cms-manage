package com.chj9.cms.common.exception;

public class NotDevelopedException extends RuntimeException {
    private static final long serialVersionUID = -5333977609817102107L;

    public NotDevelopedException() {
        super();
    }

    public NotDevelopedException(String message) {
        super(message);
    }

    public NotDevelopedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotDevelopedException(Throwable cause) {
        super(cause);
    }

    protected NotDevelopedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

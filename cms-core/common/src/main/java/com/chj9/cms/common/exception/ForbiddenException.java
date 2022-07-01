package com.chj9.cms.common.exception;

import com.chj9.cms.common.response.CmsResponse;

public class ForbiddenException extends RuntimeException {
    private final static long serialVersionUID = 5992021193625844491L;

    private String code;
    private Object payload;

    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String code, String message, Object payload) {
        super(message);
        this.code = code;
        this.payload = payload;
    }

    public ForbiddenException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ForbiddenException(CmsResponse response) {
        super(response.getMsg());
        this.code = response.getCode();
        this.payload = response.getPayload();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

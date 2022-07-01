package com.chj9.cms.common.exception;

import com.chj9.cms.common.response.CmsResponse;

public class UnauthorizedException extends RuntimeException {
    private final static long serialVersionUID = 5992021193625844491L;

    private String code;
    private Object payload;

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String code, String message, Object payload) {
        super(message);
        this.code = code;
        this.payload = payload;
    }

    public UnauthorizedException(String code, String message) {
        super(message);
        this.code = code;
    }

    public UnauthorizedException(CmsResponse response) {
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

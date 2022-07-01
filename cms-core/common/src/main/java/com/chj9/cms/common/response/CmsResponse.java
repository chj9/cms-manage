package com.chj9.cms.common.response;


import com.chj9.cms.common.exception.BadRequestException;
import com.chj9.cms.common.exception.ForbiddenException;
import com.chj9.cms.common.exception.NotDevelopedException;
import com.chj9.cms.common.exception.TurnRightException;

import java.time.Instant;

/**
 * @author Administrator
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class CmsResponse {
    public final static String SUCCESS = "0";
    public final static String REDIRECT = "302";

    private String code = "";
    private String msg = "";
    private Object payload;
    private Instant responseAt = Instant.now();

    public <E extends TurnRightException> CmsResponse(E exception) {
        this.code = exception.getCode();
        this.msg = exception.getMessage();
        this.payload = exception.getPayload();
    }

    public CmsResponse(BadRequestException exception) {
        this.code = "400";
        this.msg = exception.getMessage();
        this.payload = exception.getPayload();
    }

    public CmsResponse(ForbiddenException exception) {
        this.code = exception.getCode();
        this.msg = exception.getMessage();
        this.payload = exception.getPayload();
    }

    public CmsResponse(String code, String msg, Object payload) {
        this.code = code;
        this.msg = msg;
        this.payload = payload;
    }

    public CmsResponse(String msg, Object payload) {
        this.code = SUCCESS;
        this.msg = msg;
        this.payload = payload;
    }

    public CmsResponse(Object payload) {
        this.code = SUCCESS;
        this.payload = payload;
    }

    public CmsResponse(NotDevelopedException exception) {
        this.msg = exception.getMessage();
    }

    @SuppressWarnings("unused")
    public CmsResponse() {
        this.code = SUCCESS;
    }

    public static CmsResponse ofSuccess() {
        return new CmsResponse();
    }

    @Override
    public String toString() {
        return "TurnRightResponse{" +
            "code='" + code + '\'' +
            ", msg='" + msg + '\'' +
            ", payload=" + payload +
            '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Instant getResponseAt() {
        return responseAt;
    }

    public void setResponseAt(Instant responseAt) {
        this.responseAt = responseAt;
    }
}

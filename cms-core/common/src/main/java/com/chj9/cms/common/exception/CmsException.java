package com.chj9.cms.common.exception;


import com.chj9.cms.common.exception.status.Code;
import com.chj9.cms.common.exception.status.ErrorCode;
import com.chj9.cms.common.util.StringUtil;

/**
 * @author Administrator
 */
public class CmsException extends RuntimeException implements Code {
    private static final long serialVersionUID = 8918971145316349533L;

    private Code code;
    private String detail;
    private Object payload;

    public CmsException(Code code, String detail) {
        super(StringUtil.isEmpty(detail) ? code.getMsg() : code.getMsg() + ":" + detail + ";");
        this.code = code;
        this.detail = detail;
    }

    public CmsException(Code code, String detail, Object payload) {
        super(StringUtil.isEmpty(detail) ? code.getMsg() : code.getMsg() + ":" + detail + ";" + payload);
        this.code = code;
        this.detail = detail;
        this.payload = payload;
    }

    public CmsException(CmsException message) {
        super(message.getMsg());
        this.code = ErrorCode.getEnum(message.getCode());
        this.payload = message.getPayload();
    }

    public CmsException(Code code) {
        super(code.getMsg());
        this.code = code;
    }


    @Override
    public String getCode() {
        return code.getCode();
    }

    @Override
    public String getMsg() {
        return null;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

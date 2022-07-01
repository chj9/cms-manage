package com.chj9.cms.common.exception.status;

import java.util.Objects;

/**
 * description: 错误码
 *
 * @author liexuan
 * @date 2021-11-05 14:53
 **/
public enum ErrorCode implements Code {

    /**
     * 定义异常码，需遵循wiki优先的原则，先在wiki上抢注对应的异常码定义，然后才在本枚举类中使用
     * [系统异常代码](https://www.tapd.cn/47500486/markdown_wikis/show/#1147500486001001507)
     */
    INVALID_PARAMETER("1001", "参数不合法"),
    INVALID_PARAMETER_TYPE("1002", "参数类型不匹配"),
    SYSTEM_ERROR_TRY_AGAIN("1006", "系统错误，请稍后重试"),
    SYSTEM_VERIFY_SIGN_ERROR("1008", "签名不正确，验签失败"),
    SIGN_NOT_EXIST("1009", "操作人未配置签名"),
    TOO_MANY_REQUEST("100", "接口重复提交"),
    FixedOrderLessThanMinimum("3004", "一口价订单，商品金额不满40元，按40元收取，您确定下单吗？"),
    ;


    private final String code;
    private final String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }


    public static ErrorCode getEnum(String code) {
        for (ErrorCode ele : ErrorCode.values()) {
            if (Objects.equals(code, ele.getCode())) {
                return ele;
            }
        }
        return null;
    }
}

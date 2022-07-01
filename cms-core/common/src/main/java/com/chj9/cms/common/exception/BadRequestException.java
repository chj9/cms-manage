package com.chj9.cms.common.exception;


import com.chj9.cms.common.response.CmsResponse;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author
 */
@SuppressWarnings("unused")
public class BadRequestException extends RuntimeException {
    private final static long serialVersionUID = 2744600180962486567L;

    private Object payload;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object payload) {
        super(message);
        this.payload = payload;
    }

    public BadRequestException(CmsResponse response) {
        super(response.getMsg());
        this.payload = response.getPayload();
    }

    @SuppressWarnings("unused")
    public BadRequestException(ConstraintViolationException exception) {
        super(exception.getMessage());

        HashMap<String, String> payload = new HashMap<>();
        exception.getConstraintViolations().forEach(violation ->
            payload.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public static void throwIfTrue(boolean condition, String msg) {
        if (condition) {
            throw new BadRequestException(msg);
        }
    }

    public static Supplier<BadRequestException> supplierOf(String message) {
        return () -> new BadRequestException(message);
    }

    public static Supplier<BadRequestException> supplierOf(String message, Object payload) {
        return () -> new BadRequestException(message, payload);
    }
}

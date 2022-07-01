package com.chj9.cms.web.advice;

import com.aliyun.oss.ServiceException;
import com.chj9.cms.common.exception.BadRequestException;
import com.chj9.cms.common.exception.CmsException;
import com.chj9.cms.common.exception.ForbiddenException;
import com.chj9.cms.common.exception.InternalErrorException;
import com.chj9.cms.common.exception.NameEnumNotFoundException;
import com.chj9.cms.common.exception.NoContentException;
import com.chj9.cms.common.exception.NotDevelopedException;
import com.chj9.cms.common.exception.SystemException;
import com.chj9.cms.common.exception.UnauthorizedException;
import com.chj9.cms.common.exception.ValueEnumNotFoundException;
import com.chj9.cms.common.logging.SouthernQuietLogger;
import com.chj9.cms.common.logging.SouthernQuietLoggerFactory;
import com.chj9.cms.common.response.CmsResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BaseExceptionAdvice extends ResponseEntityExceptionHandler {
    private final static SouthernQuietLogger log = SouthernQuietLoggerFactory.getLogger(BaseExceptionAdvice.class);

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CmsException.class)
    public CmsResponse CmsExceptionHandler(CmsException e) {
        return new CmsResponse(e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(NotDevelopedException.class)
    public CmsResponse notDevelopedExceptionHandler(NotDevelopedException e) {
        return new CmsResponse(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public CmsResponse badRequestExceptionHandler(BadRequestException e) {
        return new CmsResponse(e);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContentException.class)
    public void noContentException() {
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    public CmsResponse forbiddenException(ForbiddenException e) {
        return new CmsResponse(e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public void unauthorizedException() {
    }

    @ExceptionHandler({UndeclaredThrowableException.class, AuthException.class})
    public ResponseEntity<?> authException(Exception e) {
        AuthException authException;

        if (e instanceof UndeclaredThrowableException) {
            authException = (AuthException) e.getCause();
        } else {
            authException = (AuthException) e;
        }

        if (authException != null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        headers.setContentType(MediaType.APPLICATION_JSON);

        CmsResponse CmsResponse = new CmsResponse();
        CmsResponse.setMsg(ex.getMessage());

        HashMap<String, String> payload = ex.getAllErrors().stream()
            .collect(Collectors.toMap(ObjectError::getObjectName, ObjectError::toString, (a, b) -> b, HashMap::new));

        CmsResponse.setPayload(payload);

        return new ResponseEntity<>(CmsResponse, headers, status);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
        log.message("属性校验失败")
            .context("RequestURI", request.getRequestURI())
            .context("violations", exception.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath().toString() + "=" + v.getMessage())
                .sorted()
                .reduce((prev, next) -> prev + "," + next)
                .map(result -> "[" + result + "]")
                .orElse("[]"))
            .context("message", exception.getMessage())
            .warn();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(OptimisticLockingFailureException.class)
    public void optimisticLockingFailureException(OptimisticLockingFailureException e, HttpServletRequest request) {
        log.message("乐观锁并发冲突")
            .context("RequestURI", request.getRequestURI())
            .context("Message", e.getMessage())
            .error();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalErrorException.class)
    public CmsResponse internalErrorExceptionHandler(InternalErrorException e) {
        CmsResponse CmsResponse = new CmsResponse();
        CmsResponse.setMsg(e.getMessage());
        CmsResponse.setCode("error");
        CmsResponse.setPayload(e.getPayload());
        return CmsResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {
        NameEnumNotFoundException.class,
        ServiceException.class,
        SystemException.class,
        ValueEnumNotFoundException.class})
    public CmsResponse internalSystemErrorExceptionHandler(RuntimeException e, HttpServletRequest request) {
        // TODO chenxiaobo 待优化
        log.message("系统内部错误")
            .context("RequestURI", request.getRequestURI())
            .context("Message", e.getMessage())
            .context("e", e.toString())
            .error();
        CmsResponse CmsResponse = new CmsResponse();
        CmsResponse.setMsg(e.getMessage());
        CmsResponse.setCode("error");
        return CmsResponse;
    }

    /**
     * 处理 @valid 验证失败异常信息返回
     *
     * @param exception methodArgumentNotValidException
     * @param headers   headers
     * @param status    status
     * @param request   request
     * @return responseEntity
     */
    @NotNull
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception, HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {

        headers.setContentType(MediaType.APPLICATION_JSON);
        BindingResult result = exception.getBindingResult();
        Map<String, String> payload = new HashMap<>();

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> payload.put(error.getField(), error.getDefaultMessage()));
        }
        CmsResponse response = new CmsResponse(new BadRequestException("参数校验失败"));
        response.setPayload(payload);
        return new ResponseEntity<>(response, headers, status);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(@NotNull TypeMismatchException ex, HttpHeaders headers, @NotNull HttpStatus status, WebRequest request) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        CmsResponse response = new CmsResponse(new BadRequestException("参数不合法"));
        return new ResponseEntity<>(response, headers, status);
    }
}

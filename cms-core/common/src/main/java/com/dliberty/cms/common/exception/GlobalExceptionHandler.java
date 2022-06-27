package com.dliberty.cms.common.exception;

import com.dliberty.cms.common.vo.JsonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * @author LG
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {}
	
	@ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public JsonBean jsonErrorHandler(HttpServletRequest req, CommonException e) throws Exception {
		String url = req.getRequestURL().toString();
		logger.warn("访问{}页面发生异常:{}",url,e.getMessage());
		JsonBean json = new JsonBean();
		json.setMessage(e.getMessage());
		json.setCode("1");
        return json;
    }
	
	/**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonBean errorHandler(HttpServletRequest req, Exception ex) {
    	String url = req.getRequestURL().toString();
		logger.warn("访问{}页面发生异常:{}",url,ex.getMessage());
		JsonBean json = new JsonBean();
		json.setMessage("未知错误");
		json.setCode("1");
        return json;
    }
    
    /**
     * 全局@Validated异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonBean MyMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex) {
    	String url = req.getRequestURL().toString();
		logger.warn("访问{}页面发生异常:{}",url,ex.getMessage());
		JsonBean json = new JsonBean();
		BindingResult bindingResult = ex.getBindingResult();
		json.setMessage(bindingResult.getFieldError().getDefaultMessage());
		json.setCode("1");
        return json;
    }
    
    
}

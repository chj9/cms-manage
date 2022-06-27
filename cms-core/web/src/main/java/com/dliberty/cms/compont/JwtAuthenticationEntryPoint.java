package com.dliberty.cms.compont;

import com.alibaba.fastjson.JSONObject;
import com.dliberty.cms.vo.JsonBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 认证失败处理类，返回401
 * 
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		//验证为未登陆状态会进入此方法，认证错误
        System.out.println("认证失败：" + authException.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();

        JsonBean json = new JsonBean();
        json.setCode("2");
        json.setMessage("用户未登陆");
        String body = JSONObject.toJSONString(json);

        printWriter.write(body);
        printWriter.flush();
	}

}

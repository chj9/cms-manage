package com.chj9.cms.web.compont;

import com.alibaba.fastjson.JSONObject;
import com.chj9.cms.common.vo.JsonBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限不足错误类
 * @author LG
 *
 */
@Component("restAuthenticationAccessDeniedHandler")
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
			throws IOException, ServletException {
		//登陆状态下，权限不足执行该方法
        System.out.println("权限不足：" + e.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        
        JsonBean json = new JsonBean();
        json.setCode("1");
        json.setMessage(e.getMessage());
        String body = JSONObject.toJSONString(json);
        printWriter.write(body);
        printWriter.flush();

	}

}

package com.dliberty.cms.controller;

import java.util.List;

import com.dliberty.cms.common.exception.CommonException;
import com.dliberty.cms.common.vo.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.dto.UmsAdminLoginParam;
import com.dliberty.cms.service.UmsRoleService;
import com.dliberty.cms.service.UserLoginService;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@RestController
@RequestMapping("/admin")
public class UmsAdminController extends BaseController {
	@Autowired
	private UserLoginService userLoginService; 
    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsRoleService umsRoleService; 
    @Value("${app.weixin.appid}")
	private String appId;
	@Value("${app.weixin.secret}")
	private String secret;


    @PostMapping(value = "/login")
    public JsonBean login(@RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = userLoginService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            throw new CommonException("用户名或密码错误");
        }
        List<String> roleCodes = umsRoleService.selectCode(getUserId());
        JsonBean json = new JsonBean();
        json.put("token", token);
        json.put("tokenHead", tokenHead);
        json.put("roleCodes", roleCodes);
        return json;
    }
    
}

package com.chj9.cms.service;

import com.chj9.cms.common.exception.CommonException;
import com.chj9.cms.common.util.JwtTokenUtil;
import com.chj9.cms.api.entity.UsersEntity;
import com.chj9.cms.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UsersService usersService;
	@Autowired
	@Qualifier("myUserDetailsService")
	private UserDetailsService myUserDetailsService;
	
	/**
	 * 注册
	 */
	public void register(String userName, String password) {
		usersService.register(userName, password);
	}
	
	
	/**
	 * 后台用户登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	public String login(String username,String password) {
		String token = null;
        //密码需要客户端加密后传递
		UsersEntity users = usersService.selectUserByOpenId(username);
		if (null == users) {
			throw new CommonException("用户名不存在");
		}
		
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            token = jwtTokenUtil.generateToken(userDetails,null);
        } catch (AuthenticationException e) {
            logger.warn("登录异常:{}", e.getMessage());
        }
        return token;
	}
	
	public String loginWeixin(String openId) {
		
		logger.info("openId={}登陆",openId);
		if (StringUtils.isEmpty(openId)) {
			throw new CommonException("openId is null");
		}
		//默认注册
		register(openId,openId);
		
        return login(openId, openId);
		
	}

}

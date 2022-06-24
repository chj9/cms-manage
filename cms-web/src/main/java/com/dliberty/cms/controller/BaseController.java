package com.dliberty.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.dliberty.cms.dao.entity.Users;
import com.dliberty.cms.service.UsersService;

@Controller
public class BaseController {
	
	@Autowired
	private UsersService usersService;

	public Long getUserId() {
		String userName = getUserName();
		Users users = usersService.selectUserByOpenId(userName);
		if (users != null) {
			return users.getId();
		}
		return null;
	}
	
	public String getUserName() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		String userName = userDetails.getUsername();
		return userName;
	}
}

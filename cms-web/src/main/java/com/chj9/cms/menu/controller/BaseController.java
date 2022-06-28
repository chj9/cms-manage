package com.chj9.cms.menu.controller;

import com.chj9.cms.api.entity.UsersEntity;
import com.chj9.cms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	
	@Autowired
	private UsersService usersService;

	public Long getUserId() {
		String userName = getUserName();
		UsersEntity users = usersService.selectUserByOpenId(userName);
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

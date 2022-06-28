package com.chj9.cms.web.service;

import java.util.List;

import com.chj9.cms.api.dto.AdminUserDetails;
import com.chj9.cms.service.UmsRoleService;
import com.chj9.cms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dliberty.cms.dao.entity.UmsPermission;
import com.dliberty.cms.dao.entity.Users;
import com.dliberty.cms.exception.CommonException;

@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private UmsRoleService umsRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users users = usersService.selectUserByOpenId(username);
		if (null == users) {
			throw new CommonException("用户名不存在");
		}
		List<UmsPermission> permissionList = umsRoleService.getPermissionListByAdminId(users.getId());
		return new AdminUserDetails(users,permissionList);
		
	}

}
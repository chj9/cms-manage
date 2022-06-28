package com.chj9.cms.menu.service;

import com.chj9.cms.common.exception.CommonException;
import com.chj9.cms.api.dto.AdminUserDetails;
import com.chj9.cms.api.entity.UmsPermissionEntity;
import com.chj9.cms.api.entity.UsersEntity;
import com.chj9.cms.service.UmsRoleService;
import com.chj9.cms.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private UmsRoleService umsRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsersEntity users = usersService.selectUserByOpenId(username);
		if (null == users) {
			throw new CommonException("用户名不存在");
		}
		List<UmsPermissionEntity> permissionList = umsRoleService.getPermissionListByAdminId(users.getId());
		return new AdminUserDetails(users,permissionList);
		
	}

}

package com.dliberty.cms.service;

import com.dliberty.cms.common.exception.CommonException;
import com.dliberty.cms.dto.AdminUserDetails;
import com.dliberty.cms.entity.UmsPermissionEntity;
import com.dliberty.cms.entity.UsersEntity;
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

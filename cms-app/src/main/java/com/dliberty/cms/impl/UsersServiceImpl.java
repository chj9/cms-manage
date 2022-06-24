package com.dliberty.cms.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.dao.entity.Users;
import com.dliberty.cms.dao.mapper.UsersMapper;
import com.dliberty.cms.service.UsersService;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author GuoJingtao
 * @since 2019-03-20
 */
@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Users selectUserByOpenId(String userName) {
		QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("username", userName);
		queryWrapper.orderByDesc("id");
		List<Users> selectList = baseMapper.selectList(queryWrapper);
		if (selectList.size() > 0) {
			return selectList.get(0);
		}
		return null;
	}

	@Override
	public Users register(String userName,String password) {
		try {
			Users users = selectUserByOpenId(userName);
			if (users != null) {
				return users;
			}
			users = new Users();
			users.setUsername(userName);
			users.setCreateTime(new Date());
			users.setUpdateTime(new Date());
			users.setStatus(1);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encode = encoder.encode(password);
			users.setPassword(encode);
			save(users);
			return users;
		} catch (Exception e) {
			logger.info("保存用户信息发生异常{}",e.getMessage());
			return null;
		}
		
		
	}


}

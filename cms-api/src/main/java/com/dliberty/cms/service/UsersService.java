package com.dliberty.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.Users;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author GuoJingtao
 * @since 2019-03-20
 */
public interface UsersService extends IService<Users> {

	/**
	 * 根据openId查找用户
	 * @param openId
	 * @return
	 */
	Users selectUserByOpenId(String openId);
	
	/**
	 * 微信用户注册
	 * @param openId
	 * @return
	 */
	Users register(String openId,String password);
	
}

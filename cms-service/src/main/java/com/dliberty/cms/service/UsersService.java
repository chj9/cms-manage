package com.dliberty.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.entity.UsersEntity;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author GuoJingtao
 * @since 2019-03-20
 */
public interface UsersService extends IService<UsersEntity> {

	/**
	 * 根据openId查找用户
	 * @param openId
	 * @return
	 */
	UsersEntity selectUserByOpenId(String openId);
	
	/**
	 * 微信用户注册
	 * @param openId
	 * @return
	 */
	UsersEntity register(String openId,String password);
	
}

package com.chj9.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chj9.cms.api.entity.CmsMenuCollectionEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-14
 */
public interface CmsMenuCollectionService extends IService<CmsMenuCollectionEntity> {
	
	/**
	 * 收藏
	 * @param userId
	 * @param menuId
	 * @return
	 */
	CmsMenuCollectionEntity collection(Long userId,Long menuId);
	
	/**
	 * 取消收藏
	 * @param userId
	 * @param menuId
	 * @return
	 */
	void removeCollection(Long userId,Long menuId);
	
	/**
	 * 根据userId 和 menuId 查询
	 * @param userId
	 * @param menuId
	 * @return
	 */
	List<CmsMenuCollectionEntity> selectByMenuId(Long userId,Long menuId);
	
	/**
	 * 查询用户收藏的ids
	 * @param userId
	 * @return
	 */
	List<Integer> selectByUserId(Long userId);
	

}

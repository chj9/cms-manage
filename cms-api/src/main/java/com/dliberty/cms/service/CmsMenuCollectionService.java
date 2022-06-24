package com.dliberty.cms.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuCollection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-14
 */
public interface CmsMenuCollectionService extends IService<CmsMenuCollection> {
	
	/**
	 * 收藏
	 * @param userId
	 * @param menuId
	 * @return
	 */
	CmsMenuCollection collection(Long userId,Integer menuId);
	
	/**
	 * 取消收藏
	 * @param userId
	 * @param menuId
	 * @return
	 */
	void removeCollection(Long userId,Integer menuId);
	
	/**
	 * 根据userId 和 menuId 查询
	 * @param userId
	 * @param menuId
	 * @return
	 */
	List<CmsMenuCollection> selectByMenuId(Long userId,Integer menuId);
	
	/**
	 * 查询用户收藏的ids
	 * @param userId
	 * @return
	 */
	List<Integer> selectByUserId(Long userId);
	

}

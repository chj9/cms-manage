package com.chj9.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chj9.cms.api.entity.CmsMenuLabelRelationEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-09-05
 */
public interface CmsMenuLabelRelationService extends IService<CmsMenuLabelRelationEntity> {
	
	/**
	 * 根据menuId删除关联关系
	 * @param menuId
	 */
	void deleteByMenuId(Long menuId);
	
	/**
	 * 保存关联关系
	 * @param menuId
	 * @param labels
	 */
	void save(Long menuId,List<Integer> labels);

}

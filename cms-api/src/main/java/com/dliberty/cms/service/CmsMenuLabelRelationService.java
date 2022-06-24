package com.dliberty.cms.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuLabelRelation;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-09-05
 */
public interface CmsMenuLabelRelationService extends IService<CmsMenuLabelRelation> {
	
	/**
	 * 根据menuId删除关联关系
	 * @param menuId
	 */
	void deleteByMenuId(Integer menuId);
	
	/**
	 * 保存关联关系
	 * @param menuId
	 * @param labels
	 */
	void save(Integer menuId,List<Integer> labels);

}

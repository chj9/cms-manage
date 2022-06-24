package com.dliberty.cms.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuStep;
import com.dliberty.cms.vo.CmsMenuStepParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuStepService extends IService<CmsMenuStep> {

	/**
	 * 保存步骤信息
	 * @param stepList
	 * @param menuId
	 */
	void saveStep(List<CmsMenuStep> stepList,Integer menuId);
	
	/**
	 * 查询步骤
	 * @param menuId
	 * @return
	 */
	List<CmsMenuStep> selectByMenuId(Integer menuId);
	
	/**
	 * 根据menuId 删除
	 * @param menuId
	 */
	void delete(Integer menuId);
	
	/**
	 * 修改步骤
	 * @param menuId
	 * @param param
	 */
	void update(Integer menuId,CmsMenuStepParam param);
}

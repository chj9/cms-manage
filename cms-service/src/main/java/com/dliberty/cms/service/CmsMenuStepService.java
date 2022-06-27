package com.dliberty.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuStep;
import com.dliberty.cms.vo.CmsMenuStepParam;

import java.util.List;

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
	void saveStep(List<CmsMenuStep> stepList,Long menuId);
	
	/**
	 * 查询步骤
	 * @param menuId
	 * @return
	 */
	List<CmsMenuStep> selectByMenuId(Long menuId);
	
	/**
	 * 根据menuId 删除
	 * @param menuId
	 */
	void delete(Long menuId);
	
	/**
	 * 修改步骤
	 * @param menuId
	 * @param param
	 */
	void update(Long menuId,CmsMenuStepParam param);
}

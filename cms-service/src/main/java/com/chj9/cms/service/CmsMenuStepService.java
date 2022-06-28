package com.chj9.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chj9.cms.api.entity.CmsMenuStepEntity;
import com.chj9.cms.api.vo.CmsMenuStepParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuStepService extends IService<CmsMenuStepEntity> {

	/**
	 * 保存步骤信息
	 * @param stepList
	 * @param menuId
	 */
	void saveStep(List<CmsMenuStepEntity> stepList,Long menuId);
	
	/**
	 * 查询步骤
	 * @param menuId
	 * @return
	 */
	List<CmsMenuStepEntity> selectByMenuId(Long menuId);
	
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
	void update(Long menuId, CmsMenuStepParam param);
}

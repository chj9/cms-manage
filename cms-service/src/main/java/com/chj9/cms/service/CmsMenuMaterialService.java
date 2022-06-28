package com.chj9.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.api.vo.CmsMenuMaterialParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuMaterialService extends IService<CmsMenuMaterialEntity> {

	/**
	 * 保存用料
	 * @param material
	 * @param menuId
	 */
	void saveMaterial(List<CmsMenuMaterialEntity> materialList,Long menuId);
	
	/**
	 * 查询材料
	 * @param menuId
	 * @return
	 */
	List<CmsMenuMaterialEntity> selectByMenuId(Long menuId);
	
	/**
	 * 根据menuId删除
	 * @param menuId
	 */
	void deleteByMenuId(Long menuId);
	
	/**
	 * 修改用料
	 * @param menuId
	 * @param param
	 */
	void update(Long menuId, CmsMenuMaterialParam param);
}

package com.dliberty.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuMaterial;
import com.dliberty.cms.vo.CmsMenuMaterialParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuMaterialService extends IService<CmsMenuMaterial> {

	/**
	 * 保存用料
	 * @param material
	 * @param menuId
	 */
	void saveMaterial(List<CmsMenuMaterial> materialList,Long menuId);
	
	/**
	 * 查询材料
	 * @param menuId
	 * @return
	 */
	List<CmsMenuMaterial> selectByMenuId(Long menuId);
	
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
	void update(Long menuId,CmsMenuMaterialParam param);
}

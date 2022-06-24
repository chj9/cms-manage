package com.dliberty.cms.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuMaterial;
import com.dliberty.cms.vo.CmsMenuMaterialParam;

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
	void saveMaterial(List<CmsMenuMaterial> materialList,Integer menuId);
	
	/**
	 * 查询材料
	 * @param menuId
	 * @return
	 */
	List<CmsMenuMaterial> selectByMenuId(Integer menuId);
	
	/**
	 * 根据menuId删除
	 * @param menuId
	 */
	void deleteByMenuId(Integer menuId);
	
	/**
	 * 修改用料
	 * @param menuId
	 * @param param
	 */
	void update(Integer menuId,CmsMenuMaterialParam param);
}

package com.dliberty.cms.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuCategory;
import com.dliberty.cms.vo.CmsMenuCateParam;
import com.dliberty.cms.vo.CmsMenuCategoryQueryParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuCategoryService extends IService<CmsMenuCategory> {
	

	/**
	 * 根据name 和父级Id 查询
	 * @param cateName
	 * @param parentId
	 * @return
	 */
	CmsMenuCategory selectByNameAndParentId(String cateName,Integer parentId);
	/**
	 * 保存类别
	 * @param cate
	 * @param parentId
	 * @return
	 */
	CmsMenuCategory save(String cateName,Integer parentId);
	
	/**
	 * 根据parentId查询
	 * @param parentId
	 * @return
	 */
	List<CmsMenuCategory> selectByParentId(Integer parentId);
	
	/**
	 * 根据cateName 查询最底级的数据
	 * @param cateName
	 * @return
	 */
	List<CmsMenuCategory> selectLastCategory(String cateName);
	
	/**
	 * 分页
	 * @param parentId
	 * @param param
	 * @return
	 */
	IPage<CmsMenuCategory> listPage(Integer parentId,CmsMenuCategoryQueryParam param);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 创建分类
	 * @param param
	 * @return
	 */
	CmsMenuCategory create(CmsMenuCateParam param);
	
	/**
	 * 修改分类
	 * @param param
	 * @return
	 */
	CmsMenuCategory update(Integer id,CmsMenuCateParam param);
}

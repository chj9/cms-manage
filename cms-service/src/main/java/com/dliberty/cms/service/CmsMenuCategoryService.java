package com.dliberty.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.common.page.PageParam;
import com.dliberty.cms.entity.CmsMenuCategoryEntity;
import com.dliberty.cms.vo.CmsMenuCateParam;
import com.dliberty.cms.vo.CmsMenuCategoryQueryParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuCategoryService extends IService<CmsMenuCategoryEntity> {
	

	/**
	 * 根据name 和父级Id 查询
	 * @param cateName
	 * @param parentId
	 * @return
	 */
	CmsMenuCategoryEntity selectByNameAndParentId(String cateName,Integer parentId);
	/**
	 * 保存类别
	 * @param cate
	 * @param parentId
	 * @return
	 */
	CmsMenuCategoryEntity save(String cateName,Integer parentId);
	
	/**
	 * 根据parentId查询
	 * @param parentId
	 * @return
	 */
	List<CmsMenuCategoryEntity> selectByParentId(Integer parentId);
	
	/**
	 * 根据cateName 查询最底级的数据
	 * @param cateName
	 * @return
	 */
	List<CmsMenuCategoryEntity> selectLastCategory(String cateName);
	
	/**
	 * 分页
	 * @param parentId
	 * @param param
	 * @return
	 */
	public PageDTO<CmsMenuCategoryEntity> listPage(Integer parentId, PageParam pageParam, CmsMenuCategoryQueryParam param);
	
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
	CmsMenuCategoryEntity create(CmsMenuCateParam param);
	
	/**
	 * 修改分类
	 * @param param
	 * @return
	 */
	CmsMenuCategoryEntity update(Integer id,CmsMenuCateParam param);
}

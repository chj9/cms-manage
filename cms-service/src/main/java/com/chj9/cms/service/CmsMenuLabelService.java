package com.chj9.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import com.chj9.cms.api.vo.CmsMenuLabelParam;
import com.chj9.cms.api.vo.CmsMenuLabelQueryParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuLabelService extends IService<CmsMenuLabelEntity> {
	
	/**
	 * 根据menuId 查询 所属标签
	 * @param menuId
	 * @return
	 */
	List<CmsMenuLabelEntity> selectByMenuId(Long menuId);
	
	/**
	 * 查询所有不分页
	 * @return
	 */
	List<CmsMenuLabelEntity> listAll();
	
	/**
	 * 查询所有分页
	 * @return
	 */
	PageDTO<CmsMenuLabelEntity> listPage(PageParam pageParam, CmsMenuLabelQueryParam param);
	
	/**
	 * 创建
	 * @param param
	 * @return
	 */
	CmsMenuLabelEntity create(CmsMenuLabelParam param);
	
	/**
	 * 修改
	 * @param id
	 * @param param
	 * @return
	 */
	CmsMenuLabelEntity update(Long id,CmsMenuLabelParam param);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id);
	

}

package com.dliberty.cms.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuLabel;
import com.dliberty.cms.vo.CmsMenuLabelParam;
import com.dliberty.cms.vo.CmsMenuLabelQueryParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuLabelService extends IService<CmsMenuLabel> {
	
	/**
	 * 根据menuId 查询 所属标签
	 * @param menuId
	 * @return
	 */
	List<CmsMenuLabel> selectByMenuId(Integer menuId);
	
	/**
	 * 查询所有不分页
	 * @return
	 */
	List<CmsMenuLabel> listAll();
	
	/**
	 * 查询所有分页
	 * @return
	 */
	IPage<CmsMenuLabel> listPage(CmsMenuLabelQueryParam param);
	
	/**
	 * 创建
	 * @param param
	 * @return
	 */
	CmsMenuLabel create(CmsMenuLabelParam param);
	
	/**
	 * 修改
	 * @param id
	 * @param param
	 * @return
	 */
	CmsMenuLabel update(Integer id,CmsMenuLabelParam param);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Integer id);
	

}

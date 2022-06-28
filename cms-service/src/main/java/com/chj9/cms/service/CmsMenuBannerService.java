package com.chj9.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.api.entity.CmsMenuBannerEntity;
import com.chj9.cms.api.vo.CmsMenuBannerParam;
import com.chj9.cms.api.vo.CmsMenuBannerQueryParam;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-09-03
 */
public interface CmsMenuBannerService extends IService<CmsMenuBannerEntity> {
	
	/**
	 * 查询所有
	 * @return
	 */
	List<CmsMenuBannerEntity> listAll(Integer bannerType);
	/**
	 * 查询所有分页
	 * @return
	 */
	PageDTO<CmsMenuBannerEntity> listPage(PageParam pageParam, CmsMenuBannerQueryParam param);
	
	/**
	 * 创建
	 * @param param
	 * @return
	 */
	CmsMenuBannerEntity create(CmsMenuBannerParam param);
	
	/**
	 * 修改
	 * @param id
	 * @param param
	 * @return
	 */
	CmsMenuBannerEntity update(Integer id, CmsMenuBannerParam param);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Integer id);

}

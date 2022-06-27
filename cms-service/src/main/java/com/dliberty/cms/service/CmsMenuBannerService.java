package com.dliberty.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.entity.CmsMenuBannerEntity;
import com.dliberty.cms.vo.CmsMenuBannerParam;
import com.dliberty.cms.vo.CmsMenuBannerQueryParam;


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
	IPage<CmsMenuBannerEntity> listPage(CmsMenuBannerQueryParam param);
	
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
